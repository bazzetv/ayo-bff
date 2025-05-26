package com.ayo.bff.routes

import com.ayo.bff.application.extractUserId
import com.ayo.bff.application.receiveCatching
import com.ayo.bff.database.*
import com.ayo.bff.database.ProgramTable.fetchAllPublishedPrograms
import com.ayo.bff.utils.UUIDSerializer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.*

fun Route.programRoutes() {
    authenticate("auth-jwt") {

        get("/private/programs") {
            val userId = call.extractUserId() ?: return@get

            val allPrograms = fetchAllPublishedPrograms()
            val userPrograms = UserProgramTable.fetchUserPrograms(userId)

            val started = userPrograms.filter { !it.finished }.map { it.programId }.toSet()
            val finished = userPrograms.filter { it.finished }.map { it.programId }.toSet()

            val startedPrograms = allPrograms.filter { it.id in started && it.id !in finished }
            val finishedPrograms = allPrograms.filter { it.id in finished }
            val notStartedPrograms = allPrograms.filter { it.id !in started && it.id !in finished }

            call.respond(
                ProgramListResponseDTO(
                    started = startedPrograms,
                    finished = finishedPrograms,
                    notStarted = notStartedPrograms
                )
            )
        }

        get("/private/programs/{id}") {
            val userId = call.extractUserId() ?: return@get
            val programId = call.parameters["id"]?.let { runCatching { UUID.fromString(it) }.getOrNull() }
                ?: return@get call.respond(HttpStatusCode.BadRequest, "ID invalide")

            val program = ProgramTable.getById(programId)
                ?: return@get call.respond(HttpStatusCode.NotFound, "Programme introuvable")

            val userProgress = UserProgramTable.fetchUserProgramByIds(userId, programId)

            val status = when {
                userProgress == null -> "not_started"
                userProgress.finished -> "finished"
                else -> "started"
            }

            call.respond(
                ProgramResponseDTO(
                    program = program,
                    currentDay = userProgress?.currentDay?.toString(),
                    currentWeek = userProgress?.currentWeek?.toString(),
                    completedDays = userProgress?.completedDays?:emptyMap(),
                    status = status
                )
            )
        }

        post("/private/programs/subscribe") {
            val userId = call.extractUserId() ?: return@post
            val body = call.receiveCatching<SubscribeRequestDTO>().getOrElse {
                return@post call.respond(HttpStatusCode.BadRequest, "Requête invalide")
            }

            val existing = UserProgramTable.fetchUserProgramByIds(userId, UUID.fromString(body.programId))
            if (existing != null) {
                return@post call.respond(ProgramStartResponseDTO(
                    message = "Programme déjà existant",
                    progressId = existing.id,
                    completedDays = existing.completedDays,
                    currentWeek = existing.currentWeek,
                    currentDay = existing.currentDay
                ))
            }

            val newProgram = UserProgramDTO(
                id = UUID.randomUUID(),
                userId = userId,
                programId = UUID.fromString(body.programId),
                currentWeek = 1,
                currentDay = 1,
                completedDays = mapOf(1 to emptyList()),
                currentTraining = null,
                startedAt = java.time.Instant.now(),
                updatedAt = java.time.Instant.now(),
                finished = false
            )

            UserProgramTable.insertUserProgram(newProgram)

            call.respond(HttpStatusCode.Created, ProgramStartResponseDTO(
                message = "Programme démarré avec succès",
                progressId = newProgram.id,
                completedDays = newProgram.completedDays,
                currentWeek = newProgram.currentWeek,
                currentDay = newProgram.currentDay
            ))
        }
    }
}

@Serializable
data class SubscribeRequestDTO(
    val programId: String
)

@Serializable
data class ProgramStartResponseDTO(
    val message: String,
    @Serializable(with = UUIDSerializer::class)
    val progressId: UUID,
    val completedDays: Map<Int, List<Int>>,
    val currentWeek: Int,
    val currentDay: Int
)

@Serializable
data class ProgramListResponseDTO(
    val started: List<ProgramPreviewDto>,
    val finished: List<ProgramPreviewDto>,
    val notStarted: List<ProgramPreviewDto>
)

@Serializable
data class ProgramResponseDTO(
    val program: FullProgramDTO,
    val currentDay: String? = null,
    val currentWeek: String? = null,
    val completedDays: Map<Int, List<Int>> = emptyMap(),
    val status: String
)
