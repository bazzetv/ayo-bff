package com.terra.bff.routes

import com.terra.bff.database.AuthGoogleTable
import com.terra.bff.database.AuthPasswordTable
import com.terra.bff.database.UsersTable
import com.terra.bff.utils.generateJwt
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
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

// Variables d’environnement
val BFF_CALLBACK_URL = System.getenv("BFF_CALLBACK_URL") ?: "http://localhost:8080/auth/callback"

@Serializable
data class RegisterRequest(val email: String, val password: String)

@Serializable
data class RegisterResponse(
    val message: String,
    val userId: String,
    val token: String
)

fun Route.authRoutes() {

    route("/auth") {

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

                val userId = UsersTable.findOrCreateUserByGoogleId(email, googleId)

                val jwt = generateJwt(userId, email)
                call.respondRedirect("$frontendRedirectUri?token=$jwt")
            } catch (e: Exception) {
                call.application.log.error("Erreur OAuth: ${e.message}")
                call.respond(HttpStatusCode.InternalServerError, "Erreur interne")
            }
        }
    }
}