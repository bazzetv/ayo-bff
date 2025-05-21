package com.ayo.bff.routes

import com.ayo.bff.application.extractUserId
import com.ayo.bff.application.receiveCatching
import com.ayo.bff.database.CurrentTrainingDTO
import com.ayo.bff.database.UserProgramTable
import com.ayo.bff.database.UserTrainingDTO
import com.ayo.bff.database.UserTrainingTable
import com.ayo.bff.utils.UUIDSerializer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

fun Route.trainingRoutes() {
    authenticate("auth-jwt") {

        get("/private/training/current") {
            val userId = call.extractUserId() ?: return@get call.respond(HttpStatusCode.Unauthorized)

            val userPrograms = UserProgramTable.fetchUserPrograms(userId)

            val currentTraining = userPrograms.firstNotNullOfOrNull { program ->
                val training = UserTrainingTable.getUserTrainingByProgressId(program.id)
                if (training != null && !training.finished) {
                    training
                } else null
            }

            if (currentTraining == null) {
                return@get call.respond(HttpStatusCode.NotFound, "Aucun entraînement en cours")
            }

            call.respond(currentTraining)
        }

        put("/private/training/update") {
            val userId = call.extractUserId() ?: return@put call.respond(HttpStatusCode.Unauthorized)

            val body = call.receiveCatching<UpdateCurrentTrainingRequest>().getOrElse {
                it.printStackTrace()
                return@put call.respond(HttpStatusCode.BadRequest, "Requête invalide")
            }

            val userProgram = UserProgramTable.fetchUserProgramByIds(userId, body.programId)
                ?: return@put call.respond(HttpStatusCode.NotFound, "Programme introuvable")

            val existingTraining = UserTrainingTable.getUserTrainingByProgressId(userProgram.id)

            val trainingToPersist = UserTrainingDTO(
                id = existingTraining?.id ?: UUID.randomUUID(),
                progressId = userProgram.id,
                programId = userProgram.programId,
                weekIndex = body.currentTraining.weekIndex,
                dayIndex = body.currentTraining.dayIndex,
                name = body.currentTraining.name,
                exercises = body.currentTraining.exercises,
                durationInSeconds = existingTraining?.durationInSeconds ?: 0,
                startedAt = existingTraining?.startedAt ?: Instant.now(),
                updatedAt = Instant.now(),
                finished = false
            )

            val updated = if (existingTraining != null) {
                UserTrainingTable.updateUserTraining(existingTraining.id, trainingToPersist)
            } else {
                UserTrainingTable.createUserTraining(trainingToPersist)
                1
            }

            if (updated == 0) {
                return@put call.respond(HttpStatusCode.InternalServerError, "Erreur lors de la mise à jour de l'entraînement")
            }

            call.respond(UpdateTrainingResponse("Progress mis à jour"))
        }

        post("/private/training/finish") {
            call.extractUserId() ?: return@post call.respond(HttpStatusCode.Unauthorized)

            val body = runCatching { call.receive<FinishTrainingRequestDTO>() }.getOrElse {
                return@post call.respond(HttpStatusCode.BadRequest, "Requête invalide")
            }

            val updatedRows = UserProgramTable.markProgramFinished(body.programId)
            if (updatedRows == 0) {
                return@post call.respond(HttpStatusCode.NotFound, "Programme introuvable ou non autorisé")
            }

            call.respond(HttpStatusCode.OK, "Programme marqué comme terminé")
        }
    }
}

@Serializable
data class UpdateTrainingResponse(
    val message: String
)

@Serializable
data class UpdateCurrentTrainingRequest(
    @Serializable(with = UUIDSerializer::class)
    val programId: UUID,
    val currentTraining: CurrentTrainingDTO
)

@Serializable
data class FinishTrainingRequestDTO(
    @Serializable(with = UUIDSerializer::class)
    val programId: UUID
)