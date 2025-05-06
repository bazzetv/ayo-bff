package com.ayo.bff.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtUtils {
    private val JWT_SECRET = System.getenv("JWT_SECRET") ?: "monSuperSecretJWT"
    private val REFRESH_SECRET = System.getenv("REFRESH_SECRET") ?: "monSuperSecretRefreshJWT"

    // 🔹 Expiration des tokens
    private const val ACCESS_TOKEN_EXPIRATION = 1 * 60 * 60 * 1000 // 1 heure
    private const val REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000 // 7 jours

    /**
     * Génère un Access Token JWT (valide 1h)
     */
    fun generateJwt(userId: UUID, email: String): String {
        return JWT.create()
            .withIssuer("terraai")
            .withClaim("user_id", userId.toString())
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC256(JWT_SECRET))
    }

    /**
     * Génère un Refresh Token JWT (valide 7 jours)
     */
    fun generateRefreshToken(userId: UUID, email: String): String {
        return JWT.create()
            .withIssuer("terraai")
            .withClaim("user_id", userId.toString())
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC256(REFRESH_SECRET))
    }

    /**
     * Vérifie et rafraîchit un token expiré
     * Retourne un nouveau Access Token si le Refresh Token est valide.
     */
    fun refreshAccessToken(refreshToken: String): String? {
        return try {
            val decodedJWT = JWT.require(Algorithm.HMAC256(REFRESH_SECRET))
                .withIssuer("terraai")
                .build()
                .verify(refreshToken)

            val userId = decodedJWT.getClaim("user_id").asString()
            val email = decodedJWT.getClaim("email").asString()

            // Générer un nouveau token d'accès
            generateJwt(UUID.fromString(userId), email)
        } catch (e: Exception) {
            null // Refresh Token invalide ou expiré
        }
    }
}