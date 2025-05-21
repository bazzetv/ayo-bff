package com.ayo.bff.application

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.receive
import java.util.UUID

val jwtSecret = "monSuperSecretJWT"

fun Application.configureSecurity() {
    authentication {
        jwt("auth-jwt") {
            realm = "terraai"
            verifier(
                JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("terraai")
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("user_id").asString() != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
}

fun ApplicationCall.extractUserId(): UUID? {
    return this.principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("user_id")
        ?.asString()
        ?.let { runCatching { UUID.fromString(it) }.getOrNull() }
}

suspend inline fun <reified T : Any> ApplicationCall.receiveCatching(): Result<T> {
    return runCatching { receive<T>() }
}