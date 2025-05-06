package com.ayo.bff.application

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

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
