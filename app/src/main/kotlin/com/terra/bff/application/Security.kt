package com.terra.bff.application

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

// Secret JWT 🔥 Stocke-le en variable d’environnement en production !
val jwtSecret = "monSuperSecretJWT"

fun Application.configureSecurity() {
    authentication {
        jwt("auth-jwt") {  // ✅ Vérifie que ce nom correspond à celui utilisé dans "authenticate()"
            realm = "terraai"
            verifier(
                JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("terraai")  // ✅ Assure-toi que l'issuer correspond à celui du JWT
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
