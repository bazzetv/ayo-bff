package com.terra.bff.websockets

import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import java.util.concurrent.ConcurrentHashMap

val trainingSessions = ConcurrentHashMap<String, WebSocketSession>()
val generationSessions = ConcurrentHashMap<String, WebSocketSession>()

fun Route.websocketRoutes() {
    webSocket("/ws/training/{user_id}") {
        handleWebSocketSession("training", trainingSessions)
    }

    webSocket("/ws/generation/{user_id}") {
        handleWebSocketSession("generation", generationSessions)
    }
}

private suspend fun DefaultWebSocketServerSession.handleWebSocketSession(
    sessionType: String,
    sessionMap: ConcurrentHashMap<String, WebSocketSession>
) {
    val userId = call.parameters["user_id"]
        ?: return close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Missing user_id"))

    println("📢 New $sessionType WebSocket connection for user: $userId")

    sessionMap[userId] = this

    try {
        incoming.consumeEach { frame ->
            if (frame is Frame.Text) {
                println("💬 Received from $userId: ${frame.readText()}")
            }
        }
    } finally {
        sessionMap.remove(userId)
        println("❌ WebSocket $sessionType session closed for user: $userId")
    }
}
