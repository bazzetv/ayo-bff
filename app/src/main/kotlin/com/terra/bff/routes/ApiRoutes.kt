package com.terra.bff.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.terra.bff.database.AuthGoogleTable
import com.terra.bff.database.AuthPasswordTable
import com.terra.bff.database.UsersTable
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.util.*

val client = HttpClient(CIO)

// 🔥 Variables d’environnement pour éviter les hardcodages
val BFF_CALLBACK_URL = System.getenv("BFF_CALLBACK_URL") ?: "http://localhost:8080/auth/callback"
val JWT_SECRET = System.getenv("JWT_SECRET") ?: "monSuperSecretJWT"

fun generateJwt(userId: UUID, email: String): String {
    return JWT.create()
        .withIssuer("terraai")
        .withClaim("user_id", userId.toString())
        .withClaim("email", email)
        .withExpiresAt(Date(System.currentTimeMillis() + 60 * 60 * 1000)) // Expire en 1h
        .sign(Algorithm.HMAC256(JWT_SECRET))
}

@Serializable
data class RegisterRequest(val email: String, val password: String)

@Serializable
data class RegisterResponse(
    val message: String,
    val userId: String,
    val token: String
)

fun Route.apiRoutes() {

    route("/auth") {

        // 🔹 1️⃣ Redirection vers Google OAuth
        get("/login") {
            val clientId = System.getenv("GOOGLE_CLIENT_ID")
            val authUrl = "https://accounts.google.com/o/oauth2/auth" +
                    "?client_id=$clientId" +
                    "&redirect_uri=$BFF_CALLBACK_URL" +
                    "&response_type=code" +
                    "&scope=email profile" +
                    "&access_type=offline" +
                    "&prompt=consent"

            call.respondRedirect(authUrl)
        }

        // 🔹 2️⃣ Enregistrement d'un utilisateur avec email/mot de passe
        post("/register") {
            val request = call.receive<RegisterRequest>()
            val existingUser = transaction {
                UsersTable.select { UsersTable.email eq request.email }.firstOrNull()
            }

            if (existingUser != null) {
                call.respond(HttpStatusCode.Conflict, "Un utilisateur avec cet email existe déjà.")
                return@post
            }

            val userId = UUID.randomUUID()
            val hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())

            transaction {
                UsersTable.insert {
                    it[id] = userId
                    it[email] = request.email
                }
                AuthPasswordTable.insert {
                    it[AuthPasswordTable.userId] = userId
                    it[passwordHash] = hashedPassword
                }
            }

            val jwt = generateJwt(userId, request.email)
            call.respond(HttpStatusCode.Created, RegisterResponse("Utilisateur créé avec succès", userId.toString(), jwt))
        }

        // 🔹 3️⃣ Callback de Google OAuth
        get("/callback") {
            val code = call.request.queryParameters["code"]
            val frontendRedirectUri = call.request.queryParameters["redirect_uri"] ?: "exp://127.0.0.1:19000/--/"

            if (code == null) {
                call.respond(HttpStatusCode.BadRequest, "Code OAuth manquant")
                return@get
            }

            try {
                val response: String = client.post("https://oauth2.googleapis.com/token") {
                    parameter("client_id", System.getenv("GOOGLE_CLIENT_ID"))
                    parameter("client_secret", System.getenv("GOOGLE_CLIENT_SECRET"))
                    parameter("code", code)
                    parameter("grant_type", "authorization_code")
                    parameter("redirect_uri", BFF_CALLBACK_URL)
                }.body()

                val json = Json.parseToJsonElement(response).jsonObject
                val accessToken = json["access_token"]?.jsonPrimitive?.contentOrNull
                    ?: return@get call.respond(HttpStatusCode.Unauthorized, "Échec de l'échange du token")

                val userInfo: String = client.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    header("Authorization", "Bearer $accessToken")
                }.body()

                val userJson = Json.parseToJsonElement(userInfo).jsonObject
                val googleId = userJson["id"]?.jsonPrimitive?.contentOrNull ?: return@get call.respond(HttpStatusCode.BadRequest, "ID utilisateur manquant")
                val email = userJson["email"]?.jsonPrimitive?.contentOrNull ?: return@get call.respond(HttpStatusCode.BadRequest, "Email utilisateur manquant")

                val userId = transaction {
                    UsersTable.select { UsersTable.email eq email }.singleOrNull()?.get(UsersTable.id)
                        ?: run {
                            val newId = UUID.randomUUID()
                            UsersTable.insert {
                                it[id] = newId
                                it[this.email] = email
                            }
                            AuthGoogleTable.insert {
                                it[AuthGoogleTable.userId] = newId
                                it[AuthGoogleTable.googleId] = googleId
                            }
                            newId
                        }
                }

                val jwt = generateJwt(userId, email)
                call.respondRedirect("$frontendRedirectUri?token=$jwt")
            } catch (e: Exception) {
                call.application.log.error("Erreur OAuth: ${e.message}")
                call.respond(HttpStatusCode.InternalServerError, "Erreur interne")
            }
        }
    }

    authenticate("auth-jwt") {
        get("/protected") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()
            val email = principal?.payload?.getClaim("email")?.asString()

            if (userId == null || email == null) {
                call.respond(HttpStatusCode.Unauthorized, "Session invalide ou expirée")
                return@get
            }

            call.respond(HttpStatusCode.OK, RegisterResponse("Accès autorisé", userId, email))
        }
    }
}
