package com.ayo.bff.routes

import com.ayo.bff.database.FullProgramDTO
import com.ayo.bff.database.ProgramTable
import com.ayo.bff.database.ProgramTable.fetchAllPublishedPrograms
import com.ayo.bff.database.ProgressProgramDTO
import com.ayo.bff.database.ProgressTable.addProgramToUser
import com.ayo.bff.database.ProgressTable.fetchProgramsStatusByUser
import com.ayo.bff.database.ProgressTable.fetchUserProgress
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
    data class ProgramResponseDTO(
        val program: FullProgramDTO,
        val status: String,
        val currentDay: String? = null,
        val currentWeek: String? = null
    )


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
        get("/private/programs/{id}") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@get call.respond(HttpStatusCode.Unauthorized, "Authentification requise")

            val programIdParam = call.parameters["id"]
            val programId = try {
                UUID.fromString(programIdParam)
            } catch (e: Exception) {
                return@get call.respond(HttpStatusCode.BadRequest, "ID invalide")
            }

            val program = ProgramTable.getById(programId)
                ?: return@get call.respond(HttpStatusCode.NotFound, "Programme introuvable")

            val userProgress = fetchUserProgress(userId)

            val progressProgram = userProgress.firstOrNull { it.programId == programId }

            if (progressProgram == null) {
                return@get call.respond(
                    ProgramResponseDTO(
                        program = program,
                        status = "not_started"
                    )
                )
            }


            call.respond(
                ProgramResponseDTO(
                    program = program,
                    currentDay = progressProgram.currentDay.toString(),
                    currentWeek = progressProgram.currentWeek.toString(),
                    status = if (progressProgram.finished) "finished" else "started"
                )
            )
        }
        post("/private/programs/start") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim("user_id")?.asString()?.let { UUID.fromString(it) }
                ?: return@post call.respond(HttpStatusCode.Unauthorized, "Authentification requise")

            val body = try {
                call.receive<Map<String, String>>()
            } catch (e: Exception) {
                return@post call.respond(HttpStatusCode.BadRequest, "Corps de requête invalide")
            }

            val programId = body["programId"]?.let { runCatching { UUID.fromString(it) }.getOrNull() }
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Program ID manquant ou invalide")

            val existingProgress = fetchUserProgress(userId)
            if (existingProgress.any { it.programId == programId }) {
                return@post call.respond(HttpStatusCode.Conflict, "Ce programme est déjà commencé.")
            }

            val newProgress = ProgressProgramDTO(
                programId = programId,
                currentWeek = 1,
                currentDay = 1,
                finished = false
            )

            addProgramToUser(userId, newProgress)

            call.respond(HttpStatusCode.Created, mapOf("message" to "Programme démarré avec succès"))
        }
    }
}