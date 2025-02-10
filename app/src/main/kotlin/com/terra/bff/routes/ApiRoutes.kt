package com.terra.bff.routes

import com.terra.bff.websockets.generationSessions
import com.terra.bff.websockets.trainingSessions
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.websocket.*

private val client = HttpClient()

fun Route.routeApi() {
    route("/api") {

        get("/generate") {
            val prompt = call.request.queryParameters["prompt"] ?: return@get call.respondText("Missing prompt", status = io.ktor.http.HttpStatusCode.BadRequest)
            val response: String = client.get("http://localhost:8000/generate?prompt=$prompt").body()
            call.respondText(response)
        }

        post("/train") {
            val response: String = client.post("http://localhost:8000/train").body()
            call.respondText(response)
        }

        post("/optimize-prompt") {
            val userPrompt = call.request.queryParameters["prompt"] ?: return@post call.respondText("Missing prompt", status = io.ktor.http.HttpStatusCode.BadRequest)
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
