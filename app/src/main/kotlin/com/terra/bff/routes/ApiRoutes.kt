package com.terra.bff.routes

import com.terra.bff.application.UserSession
import com.terra.bff.application.log
import com.terra.bff.websockets.generationSessions
import com.terra.bff.websockets.trainingSessions
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.sessions.*
import io.ktor.websocket.*
import java.io.File

private val client = HttpClient()

fun Route.routeApi() {
    route("/api") {

        get("/generate") {
            val prompt = call.request.queryParameters["prompt"] ?: return@get call.respondText(
                "Missing prompt", status = io.ktor.http.HttpStatusCode.BadRequest
            )
            val response: String = client.get("http://localhost:8000/generate?prompt=$prompt").body()
            call.respondText(response)
        }

        post("/train") {
            val response: String = client.post("http://localhost:8000/train").body()
            call.respondText(response)
        }

        post("/optimize-prompt") {
            val userPrompt = call.request.queryParameters["prompt"] ?: return@post call.respondText(
                "Missing prompt", status = io.ktor.http.HttpStatusCode.BadRequest
            )
            val response: String = client.post("http://localhost:8000/optimize-prompt") {
                parameter("prompt", userPrompt)
            }.body()
            call.respondText(response)
        }

        post("/notify") {
            val userId = call.request.queryParameters["user_id"]
            val eventType = call.request.queryParameters["event"]

            val session = when (eventType) {
                "training_done" -> trainingSessions[userId]
                "generation_done" -> generationSessions[userId]
                else -> null
            }

            session?.send(Frame.Text("✅ $eventType terminé pour l'utilisateur $userId"))
            call.respondText("Notification envoyée")
        }
    }
}

fun Route.authRoutes() {
    authenticate("auth-oauth-google") {
        get("/auth/login") {
            call.respondRedirect("/auth/callback")
        }

        get("/auth/callback") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
            if (principal != null) {
                val accessToken = principal.accessToken
                call.sessions.set(UserSession(accessToken))
                val htmlContent = application.environment.classLoader.getResource("templates/success.html")
                    ?.readText()
                    ?: "<h1>Erreur 500</h1><p>Fichier HTML introuvable</p>"
                call.respondText(htmlContent, contentType = ContentType.Text.Html)
            } else {
                call.application.log.error("Échec de l'authentification: Principal est null")
                call.respond(HttpStatusCode.Unauthorized, "Échec de l'authentification")
            }
        }


    }

    get("/auth/session") {
        val session = call.sessions.get<UserSession>()
        if (session != null) {
            log.info("Session active : ${session.token}")
            call.respondText("Session active pour l'utilisateur: ${session.userId ?: "inconnu"}")
        } else {
            log.warn("Aucune session trouvée")
            call.respond(HttpStatusCode.Unauthorized, "Pas de session active")
        }
    }

    get("/auth/logout") {
        call.sessions.clear<UserSession>()
        val htmlContent = application.environment.classLoader.getResource("templates/logout.html")
            ?.readText()
            ?: "<h1>Erreur 500</h1><p>Fichier HTML introuvable</p>"
        call.respondText(htmlContent, contentType = ContentType.Text.Html)
    }
}
