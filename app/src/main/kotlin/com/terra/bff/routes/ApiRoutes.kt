package com.terra.bff.routes

import com.terra.bff.application.UserSession
import com.terra.bff.application.log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

private val client = HttpClient()
val userSessions = ConcurrentHashMap<String, WebSocketSession>()
val API_SECRET = System.getenv("API_SECRET") ?: "default-secret"  // À configurer dans les variables d'env

fun Route.routeApi() {

    authenticate("auth-oauth-google", strategy = AuthenticationStrategy.Optional) {
        route("/api") {

            route("/generate") {
                get {
                    val session = call.sessions.get<UserSession>()
                    if (session == null) {
                        call.respond(HttpStatusCode.Unauthorized, "Utilisateur non authentifié")
                        return@get
                    }

                    val prompt = call.request.queryParameters["prompt"]
                        ?: return@get call.respondText("Missing prompt", status = HttpStatusCode.BadRequest)

                    val response: String = client.get("http://localhost:8000/generate?prompt=$prompt").body()
                    call.respondText(response)
                }
            }

            post("/train") {
                val response: String = client.post("http://localhost:8000/train").body()
                call.respondText(response)
            }

            post("/optimize-prompt") {
                val userPrompt = call.request.queryParameters["prompt"]
                    ?: return@post call.respondText("Missing prompt", status = HttpStatusCode.BadRequest)

                val response: String = client.post("http://localhost:8000/optimize-prompt") {
                    parameter("prompt", userPrompt)
                }.body()
                call.respondText(response)
            }

            webSocket("/ws") {
                val userId = call.request.queryParameters["user_id"] ?: return@webSocket close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No user_id"))

                userSessions[userId] = this
                println("✅ Connexion WebSocket ouverte pour l'utilisateur $userId")

                try {
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            println("🔹 Message reçu du client : ${frame.readText()}")
                        }
                    }
                } finally {
                    println("❌ Fermeture de la connexion WebSocket pour $userId")
                    userSessions.remove(userId)
                }
            }

            // Endpoint appelé par le backend Python
            post("/notify") {
                val userId = call.request.queryParameters["user_id"]
                val eventType = call.request.queryParameters["event"]
                val imageUrl = call.request.queryParameters["image_url"]

                val session = userSessions[userId]

                if (session != null && imageUrl != null) {
                    session.send(Frame.Text("✅ $eventType terminé pour l'utilisateur $userId. Image : $imageUrl"))
                    call.respondText("Notification envoyée à $userId avec image.")
                } else {
                    call.respondText("Aucune connexion WebSocket pour $userId", status = HttpStatusCode.NotFound)
                }
            }
        }
    }
}

fun Routing.configureNotificationsRoutes() {
    route("/api/notifications") {
        // WebSocket pour envoyer des événements au front
        webSocket("/ws") {
            val token = call.request.queryParameters["token"]
            if (token != API_SECRET) {
                close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "⛔ Accès refusé"))
                return@webSocket
            }
            println("✅ Connexion WebSocket autorisée")
            val userId = call.request.queryParameters["user_id"] ?: return@webSocket close(
                CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No user_id")
            )
            userSessions[userId] = this
            println("✅ WebSocket ouvert pour $userId")

            try {
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        println("🔹 Message reçu du client : ${frame.readText()}")
                    }
                }
            } finally {
                println("❌ Fermeture WebSocket pour $userId")
                userSessions.remove(userId)
            }
        }

        // Endpoint HTTP appelé par le backend FastAPI
        post("/notify") {
            val authHeader = call.request.headers["Authorization"]
            if (authHeader != "Bearer $API_SECRET") {
                call.respond(HttpStatusCode.Unauthorized, "⛔ Accès refusé")
                return@post
            }
            val userId = call.request.queryParameters["user_id"]
            val session = userSessions[userId]
            if (session == null) {
                call.respond(HttpStatusCode.NotFound, "❌ Aucun WebSocket connecté pour $userId")
                return@post
            }

            val multipart = call.receiveMultipart()
            val imageBytesList = mutableListOf<ByteArray>()
            multipart.forEachPart { part ->
                if (part is PartData.FileItem && imageBytesList.size < 3) {
                    imageBytesList.add(part.streamProvider().readBytes())
                }
                part.dispose()
            }

            if (imageBytesList.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "❌ Aucune image envoyée")
                return@post
            }

            // Envoyer chaque image via WebSocket
            imageBytesList.forEach { imageBytes ->
                session.send(Frame.Binary(true, imageBytes))
            }

            call.respondText("📩 ${imageBytesList.size} image(s) envoyée(s) à $userId via WebSocket")
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

                // ✅ Chargement du fichier HTML depuis "resources/templates/"
                val htmlContent = getHtmlTemplate("success.html")
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

        // ✅ Chargement du fichier HTML depuis "resources/templates/"
        val htmlContent = getHtmlTemplate("logout.html")
        call.respondText(htmlContent, contentType = ContentType.Text.Html)
    }
}

/**
 * ✅ Fonction générique pour charger un fichier HTML depuis les templates.
 */
private fun getHtmlTemplate(fileName: String): String {
    return Application::class.java.classLoader.getResource("templates/$fileName")
        ?.readText()
        ?: "<h1>Erreur 500</h1><p>Fichier HTML introuvable</p>"
}