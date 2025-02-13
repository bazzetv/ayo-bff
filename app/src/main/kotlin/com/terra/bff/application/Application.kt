package com.terra.bff.application

import com.terra.bff.routes.authRoutes
import com.terra.bff.routes.configureNotificationsRoutes
import com.terra.bff.routes.routeApi
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

val log: Logger = LoggerFactory.getLogger("TerraAI")

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    configureSessions()
    configureSecurity()
    configureSessionAuth()
    install(CORS) {
        allowHost("localhost:5173", schemes = listOf("http")) // Autorise les requêtes depuis le frontend React
        allowCredentials = true // Permet d'envoyer les cookies avec les requêtes
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod.Options) // Nécessaire pour les pré-requêtes OPTIONS
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
    }
    install(ContentNegotiation) { json() }
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
    }

    routing {
        authRoutes()
        routeApi()
        configureNotificationsRoutes()
    }
}