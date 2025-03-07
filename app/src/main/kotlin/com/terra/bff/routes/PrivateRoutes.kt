package com.terra.bff.routes

import ReplicateClient
import com.terra.bff.database.GeneratedImagesTable
import com.terra.bff.database.GenerationRequestsTable.createGenerationRequest
import com.terra.bff.database.ModelsTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Route.generateRoutes() {

    @Serializable
    data class GenerateRequest(
        val prompt: String,
        val model: String = "Flux.1",
        val numImages: Int = 1
    )

    @Serializable
    data class ModelResponse(
        val name: String,
        val description: String,
        val imageUrl: String
    )

    @Serializable
    data class ReviewRequest(val prompt: String)

    authenticate("auth-jwt") {
        post("/private/generate") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@post call.respond(HttpStatusCode.Unauthorized, "Authentification requise")

            val request = call.receive<GenerateRequest>()

            if (request.numImages < 1 || request.numImages > 3) {
                call.respond(HttpStatusCode.BadRequest, "Le nombre d'images doit être entre 1 et 3.")
                return@post
            }

            val modelConfig = ModelsTable.getModelConfig(request.model)
            if (modelConfig == null) {
                call.respond(HttpStatusCode.BadRequest, "Modèle introuvable.")
                return@post
            }

            val replicateClient = ReplicateClient(modelConfig["apiPath"] as String)

            val parameters = modelConfig["parameters"] as JsonElement
            val version = modelConfig["version"] as String?
            val jsonParameters = parameters as? JsonObject ?: error("Les paramètres doivent être un JsonObject")

            val updatedParameters = JsonObject(jsonParameters.toMutableMap() + mapOf(
                "prompt" to JsonPrimitive(request.prompt),
                "num_outputs" to JsonPrimitive(request.numImages)
            ))

            val response = replicateClient.generateImage(updatedParameters, version)

            createGenerationRequest(
                    requestId = response.id,
                    userId = userId,
                    prompt = request.prompt,
                    model = request.model,
                    numImages = request.numImages
            )

            if (response.status == "succeeded") {
                GeneratedImagesTable.updateImagesFromReplicateResponse(response)
            }

            call.respond(HttpStatusCode.Accepted, mapOf("request_id" to response.id, "status" to "pending"))
        }

        post("/private/review") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@post call.respond(HttpStatusCode.Unauthorized, "Authentification requise")

            val request = call.receive<ReviewRequest>()

            try {
                // TODO
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Erreur lors de l'envoi de la requête au serveur de révision: ${e.message}")
            }
        }

        get("/private/models") {
            val models = transaction {
                ModelsTable.selectAll().map {
                    ModelResponse(
                        name = it[ModelsTable.name],
                        description = it[ModelsTable.description],
                        imageUrl = it[ModelsTable.imageUrl]
                    )
                }
            }
            call.respond(HttpStatusCode.OK, models)
        }

        get("/private/history") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@get call.respond(HttpStatusCode.Unauthorized, "Token invalide")

            val requestId = call.request.queryParameters["requestId"]

            val images = GeneratedImagesTable.getImagesForUserOrRequest(userId, requestId)

            call.respond(HttpStatusCode.OK, images)
        }
    }
}