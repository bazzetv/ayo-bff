package com.terra.bff.routes

import com.terra.bff.database.GeneratedImagesTable
import com.terra.bff.database.GenerationRequestsTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.*


fun Route.generateRoutes() {
    @Serializable
    data class GenerateRequest(
        val prompt: String,
        val model: String = "Flux.1",
        val numImages: Int = 1
    )

    @Serializable
    data class GenerateResponse(
        val message: String,
        val requestId: String
    )

    authenticate("auth-jwt") {
        post("/generate") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@post call.respond(HttpStatusCode.Unauthorized, "Authentification requise")

            val request = call.receive<GenerateRequest>()

            if (request.numImages < 1 || request.numImages > 3) {
                call.respond(HttpStatusCode.BadRequest, "Le nombre d'images doit être entre 1 et 3.")
                return@post
            }

            val requestId = GenerationRequestsTable.createGenerationRequest(userId, request.prompt, request.model, request.numImages)

            call.respond(HttpStatusCode.Created, GenerateResponse("Génération en cours", requestId))
        }




        get("/history") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@get call.respond(HttpStatusCode.Unauthorized, "Token invalide")

            val requestId = call.request.queryParameters["requestId"]

            val images = GeneratedImagesTable.getImagesForUserOrRequest(userId, requestId)

                call.respond(HttpStatusCode.OK, images)
        }
    }
}