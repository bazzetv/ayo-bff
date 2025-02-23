package com.terra.bff.routes

import com.terra.bff.database.GeneratedImagesTable
import com.terra.bff.database.GenerationRequestsTable.createGenerationRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.*

private val pythonHttpClient = HttpClient()

const val PYTHON_SERVER_URL = "http://192.168.1.162:8000"

@Serializable
data class ImageUpdate(
    val status: String,
    val url: String? = null
)

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

    @Serializable
    data class NotifyRequest(
        val requestId: String,
        val images: List<ImageUpdate>
    )

    @Serializable
    data class PythonGenerateResponse(
        val requestId: String,
        val status: String
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

            try {
                val response: HttpResponse = pythonHttpClient.post("$PYTHON_SERVER_URL/images/generate") {
                    url {
                        parameters.append("prompt", request.prompt)
                        parameters.append("num_images", request.numImages.toString())
                        parameters.append("model_name", request.model)
                    }
                    header("user-id", userId.toString()) // ✅ Header corrigé
                    accept(ContentType.Application.Json) // 🔹 Accepte du JSON
                }

                val responseBody = response.bodyAsText()
                println("🔹 Réponse du serveur Python : $responseBody") // 🔍 DEBUG

                val pythonResponse: PythonGenerateResponse = Json.decodeFromString(responseBody) // ⬅️ Parsing manuel

                createGenerationRequest(pythonResponse.requestId, userId, request.prompt, request.model, request.numImages)
                call.respond(HttpStatusCode.Created, GenerateResponse("Génération en cours", pythonResponse.requestId))

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Erreur lors de l'envoi de la requête au serveur de génération: ${e.message}")
            }
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

    post("/internal/notify") {
        val request = call.receive<NotifyRequest>()

        try {
            GeneratedImagesTable.updateImagesStatus(request.requestId, request.images)
            call.respond(HttpStatusCode.OK, "Mise à jour effectuée avec succès.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Erreur lors de la mise à jour des images : ${e.message}")
        }
    }
}