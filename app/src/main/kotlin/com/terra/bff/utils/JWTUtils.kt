package com.terra.bff.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

val JWT_SECRET = System.getenv("JWT_SECRET") ?: "monSuperSecretJWT"

fun generateJwt(userId: UUID, email: String): String {
    return JWT.create()
        .withIssuer("terraai")
        .withClaim("user_id", userId.toString())
        .withClaim("email", email)
        .withExpiresAt(Date(System.currentTimeMillis() + 60 * 60 * 1000)) // Expire en 1h
        .sign(Algorithm.HMAC256(JWT_SECRET))
}