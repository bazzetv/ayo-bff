package com.terra.bff.application

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import kotlinx.serialization.Serializable

@Serializable
data class UserSession(val token: String, val userId: String? = null) : Principal

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("USER_SESSION") {
            cookie.extensions["SameSite"] = "lax"
            cookie.httpOnly = true
            cookie.secure = false
            transform(SessionTransportTransformerMessageAuthentication(hex("4a6f686e446f65313233343536373839")))
        }
    }
}