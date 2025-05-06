package com.ayo.bff.utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.*
import java.math.BigInteger
import java.security.KeyFactory
import java.security.PublicKey
import java.security.Signature
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object AppleAuthUtils {

    private val client = HttpClient()

    suspend fun verifyIdentityToken(identityToken: String, applePublicKeyUrl: String): Boolean {
        try {
            val response: HttpResponse = com.ayo.bff.utils.AppleAuthUtils.client.get(applePublicKeyUrl)
            val jsonResponse = response.bodyAsText()
            val jsonKeys = Json.parseToJsonElement(jsonResponse).jsonObject["keys"]?.jsonArray ?: return false

            // ✅ Décoder le token JWT (Header, Payload, Signature)
            val jwtParts = identityToken.split(".")
            if (jwtParts.size != 3) return false
            val headerJson = String(Base64.getUrlDecoder().decode(jwtParts[0]))
            val header = Json.parseToJsonElement(headerJson).jsonObject
            val kid = header["kid"]?.jsonPrimitive?.contentOrNull ?: return false

            // ✅ Trouver la clé publique correspondante
            val key = jsonKeys.firstOrNull { it.jsonObject["kid"]?.jsonPrimitive?.contentOrNull == kid }
                ?: return false

            val modulus = key.jsonObject["n"]?.jsonPrimitive?.contentOrNull ?: return false
            val exponent = key.jsonObject["e"]?.jsonPrimitive?.contentOrNull ?: return false

            val publicKey = com.ayo.bff.utils.AppleAuthUtils.generatePublicKey(modulus, exponent)

            // ✅ Vérifier la signature du token
            return com.ayo.bff.utils.AppleAuthUtils.verifyJwtSignature(identityToken, publicKey)
        } catch (e: Exception) {
            return false
        }
    }

    private fun generatePublicKey(modulusBase64: String, exponentBase64: String): PublicKey {
        val modulusBytes = Base64.getUrlDecoder().decode(modulusBase64)
        val exponentBytes = Base64.getUrlDecoder().decode(exponentBase64)

        val modulus = BigInteger(1, modulusBytes)  // ✅ Convertir en BigInteger
        val exponent = BigInteger(1, exponentBytes)

        val spec = RSAPublicKeySpec(modulus, exponent)
        val keyFactory = KeyFactory.getInstance("RSA")

        return keyFactory.generatePublic(spec)
    }

    private fun verifyJwtSignature(identityToken: String, publicKey: PublicKey): Boolean {
        try {
            val jwtParts = identityToken.split(".")
            if (jwtParts.size != 3) {
                println("❌ JWT mal formé")
                return false
            }

            val unsignedToken = "${jwtParts[0]}.${jwtParts[1]}"
            val signatureBytes = Base64.getUrlDecoder().decode(jwtParts[2])

            // ✅ Configuration correcte de la vérification avec RSA
            val signature = Signature.getInstance("SHA256withRSA")
            signature.initVerify(publicKey)
            signature.update(unsignedToken.toByteArray())

            // ✅ Vérification correcte de la signature
            return signature.verify(signatureBytes)
        } catch (e: Exception) {
            println("❌ Erreur lors de la vérification de la signature: ${e.message}")
            return false
        }
    }
}