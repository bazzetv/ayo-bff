package com.ayo.bff.application

import com.ayo.bff.database.DatabaseFactory
import com.ayo.bff.routes.authRoutes
import com.ayo.bff.routes.programRoutes
import com.ayo.bff.routes.trainingRoutes
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*

fun main() {
    DatabaseFactory.init()
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    configureSecurity()
    install(CORS) {
        allowHost("localhost:5173", schemes = listOf("http"))
        allowCredentials = true
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
    }
    install(ContentNegotiation) { json() }

    routing {
        authRoutes()
        trainingRoutes()
        programRoutes()
    }
}