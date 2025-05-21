package com.ayo.bff.database

import com.ayo.bff.utils.InstantSerializer
import com.ayo.bff.utils.JsonElementColumnType
import com.ayo.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.Instant
import java.util.*

object UserTrainingTable : Table("user_training") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val progressId = uuid("progress_id").references(UserProgramTable.id)
    val programId = uuid("program_id").references(ProgramTable.id)
    val weekIndex = integer("week_index")
    val dayIndex = integer("day_index")
    val name = varchar("name", 255)
    val exercises = registerColumn<JsonElement>("exercises", JsonElementColumnType()).nullable()
    val durationInSeconds = integer("duration_in_seconds").default(0)
    val startedAt = timestamp("started_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
    val finished = bool("finished").default(false)
    override val primaryKey = PrimaryKey(UserProgramTable.id)

    fun createUserTraining(training: UserTrainingDTO): UUID = transaction {
        val trainingId = UUID.randomUUID()
        UserTrainingTable.insert {
            it[id] = trainingId
            it[progressId] = training.progressId
            it[programId] = training.programId
            it[weekIndex] = training.weekIndex
            it[dayIndex] = training.dayIndex
            it[name] = training.name
            it[exercises] = Json.encodeToJsonElement(ListSerializer(ExerciseProgressDTO.serializer()), training.exercises)
            it[durationInSeconds] = training.durationInSeconds
            it[finished] = training.finished
        }
        trainingId
    }

    fun getUserTrainingByProgressId(progressId: UUID): UserTrainingDTO? = transaction {
        UserTrainingTable
            .select { UserTrainingTable.progressId eq progressId }
            .mapNotNull { row ->
                try {
                    UserTrainingDTO(
                        id = row[UserTrainingTable.id],
                        progressId = row[UserTrainingTable.progressId],
                        programId = row[UserTrainingTable.programId],
                        weekIndex = row[weekIndex],
                        dayIndex = row[dayIndex],
                        name = row[name],
                        exercises = Json.decodeFromJsonElement<List<ExerciseProgressDTO>>(row[exercises]!!),
                        durationInSeconds = row[durationInSeconds],
                        startedAt = row[startedAt],
                        updatedAt = row[updatedAt],
                        finished = row[finished]
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }.singleOrNull()
    }

    fun updateUserTraining(trainingId: UUID, update: UserTrainingDTO): Int = transaction {
        UserTrainingTable.update({ UserTrainingTable.id eq trainingId }) {
            it[weekIndex] = update.weekIndex
            it[dayIndex] = update.dayIndex
            it[name] = update.name
            it[exercises] = Json.encodeToJsonElement(ListSerializer(ExerciseProgressDTO.serializer()), update.exercises)
            it[durationInSeconds] = update.durationInSeconds
            it[finished] = update.finished
            it[updatedAt] = Instant.now()
        }
    }

    fun markUserTrainingFinished(trainingId: UUID): Int = transaction {
        UserTrainingTable.update({ UserTrainingTable.id eq trainingId }) {
            it[finished] = true
            it[updatedAt] = Instant.now()
        }
    }
}

@Serializable
data class UserTrainingDTO(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    @Serializable(with = UUIDSerializer::class)
    val progressId: UUID,
    @Serializable(with = UUIDSerializer::class)
    val programId: UUID,
    val weekIndex: Int,
    val dayIndex: Int,
    val name: String,
    val exercises: List<ExerciseProgressDTO>,
    val durationInSeconds: Int,
    @Serializable(with = InstantSerializer::class)
    val startedAt: Instant,
    @Serializable(with = InstantSerializer::class)
    val updatedAt: Instant,
    val finished: Boolean
)

@Serializable
data class ExerciseProgressDTO(
    val name: String,
    val sets: List<SetProgressDTO>
)

@Serializable
data class SetProgressDTO(
    val weight: Double? = null,
    val reps: Int? = null,
    val finished: Boolean = false
)