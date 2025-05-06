package com.ayo.bff.routes

import io.ktor.server.auth.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

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
        post("/private/trainingPlans") {

        }

    }
}