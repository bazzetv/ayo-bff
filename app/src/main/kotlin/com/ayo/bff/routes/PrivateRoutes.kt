package com.ayo.bff.routes

import com.ayo.bff.database.ProgramTable.fetchAllPublishedPrograms
import com.ayo.bff.database.ProgressTable.fetchProgramsStatusByUser
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.UUID

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
        get("/private/programs") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@get call.respond(HttpStatusCode.Unauthorized, "Authentification requise")

            val allPrograms = fetchAllPublishedPrograms()

            val userProgramStatus = fetchProgramsStatusByUser(userId) // List<Map<String, Any>>

            val startedTitles = userProgramStatus.map { it["title"].toString() }.toSet()
            val finishedTitles = userProgramStatus.filter { it["finished"] == true }.map { it["title"].toString() }.toSet()

            val started = allPrograms.filter { it.title in startedTitles && it.title !in finishedTitles }
            val finished = allPrograms.filter { it.title in finishedTitles }
            val notStarted = allPrograms.filter { it.title !in startedTitles }

            call.respond(
                mapOf(
                    "started" to started,
                    "finished" to finished,
                    "not_started" to notStarted
                )
            )
        }
    }
}