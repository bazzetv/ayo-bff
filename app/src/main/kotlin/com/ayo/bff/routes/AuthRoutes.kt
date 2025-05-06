package com.ayo.bff.routes

import com.ayo.bff.database.AuthPasswordTable
import com.ayo.bff.database.AccountTable
import com.ayo.bff.utils.AppleAuthUtils
import com.ayo.bff.utils.JwtUtils
import com.ayo.bff.utils.JwtUtils.generateJwt
import com.ayo.bff.utils.JwtUtils.generateRefreshToken
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

val BFF_CALLBACK_URL = System.getenv("BFF_CALLBACK_URL") ?: "http://localhost:8080/auth/callback"

@Serializable
data class RegisterRequest(val email: String, val password: String)

@Serializable
data class RegisterResponse(
    val message: String,
    val accountId: String,
    val token: String,
    val refreshToken: String
)

@Serializable
data class AppleLoginRequest(
    val appleId: String,
    val identityToken: String,
    val email: String?,
    val fullName: String?
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

        post("/apple-login") {
            val request = call.receive<AppleLoginRequest>()
            val requestEmail = request.email?.takeIf { it.isNotBlank() } ?: "issambazze@gmail.com"

            if (request.identityToken.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Token Apple manquant")
                return@post
            }

            try {
                // ✅ Vérification du token Apple
                val applePublicKeyUrl = "https://appleid.apple.com/auth/keys"
                val isValid = AppleAuthUtils.verifyIdentityToken(request.identityToken, applePublicKeyUrl)

                if (!isValid) {
                    call.respond(HttpStatusCode.Unauthorized, "Token Apple invalide")
                    return@post
                }

                // ✅ Recherche ou création de l'utilisateur
                val accountId = AccountTable.findOrCreateAccountByAppleId(requestEmail, request.appleId)

                // ✅ Génération des tokens
                val accessToken = generateJwt(accountId, requestEmail)
                val refreshToken = generateRefreshToken(accountId, requestEmail)

                call.respond(HttpStatusCode.OK, mapOf("token" to accessToken, "refreshToken" to refreshToken))
            } catch (e: Exception) {
                call.application.log.error("Erreur lors de la connexion Apple: ${e.message}")
                call.respond(HttpStatusCode.InternalServerError, "Erreur interne")
            }
        }

        post("/register") {
            val request = call.receive<RegisterRequest>()
            val existingaccount = transaction {
                AccountTable.select { AccountTable.email eq request.email }.firstOrNull()
            }

            if (existingaccount != null) {
                call.respond(HttpStatusCode.Conflict, "Un utilisateur avec cet email existe déjà.")
                return@post
            }

            val accountId = UUID.randomUUID()
            val hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())

            transaction {
                AccountTable.insert {
                    it[id] = accountId
                    it[email] = request.email
                }
                AuthPasswordTable.insert {
                    it[AuthPasswordTable.accountId] = accountId
                    it[passwordHash] = hashedPassword
                }
            }

            val accessToken = generateJwt(accountId, request.email)
            val refreshToken = generateRefreshToken(accountId, request.email)

            call.respond(HttpStatusCode.Created, RegisterResponse("Utilisateur créé avec succès", accountId.toString(), accessToken, refreshToken))
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

                val accountInfo: String = client.get("https://www.googleapis.com/oauth2/v2/accountinfo") {
                    header("Authorization", "Bearer $accessToken")
                }.body()

                val accountJson = Json.parseToJsonElement(accountInfo).jsonObject
                val googleId = accountJson["id"]?.jsonPrimitive?.contentOrNull ?: return@get call.respond(HttpStatusCode.BadRequest, "ID utilisateur manquant")
                val email = accountJson["email"]?.jsonPrimitive?.contentOrNull ?: return@get call.respond(HttpStatusCode.BadRequest, "Email utilisateur manquant")

                val accountId = AccountTable.findOrCreateAccountByGoogleId(email, googleId)

                val accessTokenJwt = generateJwt(accountId, email)
                val refreshToken = generateRefreshToken(accountId, email)

                call.respondRedirect("$frontendRedirectUri?token=$accessTokenJwt&refreshToken=$refreshToken")
            } catch (e: Exception) {
                call.application.log.error("Erreur OAuth: ${e.message}")
                call.respond(HttpStatusCode.InternalServerError, "Erreur interne")
            }
        }

        post("/refresh") {
            val refreshToken = call.request.header("Authorization")?.removePrefix("Bearer ")

            if (refreshToken == null) {
                call.respond(HttpStatusCode.Unauthorized, "Refresh Token manquant")
                return@post
            }

            val newAccessToken = JwtUtils.refreshAccessToken(refreshToken)

            if (newAccessToken != null) {
                call.respond(HttpStatusCode.OK, mapOf("accessToken" to newAccessToken))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Refresh Token invalide ou expiré")
            }
        }
    }
}