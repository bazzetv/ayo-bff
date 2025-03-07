package com.terra.bff.routes

import ReplicateResponse
import com.terra.bff.database.GeneratedImagesTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Route.webhookRoutes() {
    post("/webhook/replicate/generation") {

        val rawBody = call.receiveText()

        val request = Json { ignoreUnknownKeys = true }.decodeFromString<ReplicateResponse>(rawBody)

        try {

            GeneratedImagesTable.updateImagesFromReplicateResponse(request)
            call.respond(HttpStatusCode.OK, "Mise à jour effectuée avec succès.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Erreur lors de la mise à jour des images : ${e.message}")
        }
    }

    post("/webhook/replicate/train") {
        TODO()
    }
}