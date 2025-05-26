package com.ayo.bff.database

import com.ayo.bff.utils.InstantSerializer
import com.ayo.bff.utils.JsonElementColumnType
import com.ayo.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

object UserProgramTable : Table("user_program") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = uuid("user_id").references(AccountTable.id)
    val programId = uuid("program_id").references(ProgramTable.id)
    val currentWeek = integer("current_week").default(1)
    val currentDay = integer("current_day").default(1)
    val completedDays = registerColumn<JsonElement>("completed_days", JsonElementColumnType())
    val startedAt = timestamp("started_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
    val finished = bool("finished").default(false)

    override val primaryKey = PrimaryKey(id)


    fun insertUserProgram(program: UserProgramDTO): UUID = transaction {
        UserProgramTable.insert {
            it[id] = program.id
            it[userId] = program.userId
            it[programId] = program.programId
            it[currentWeek] = program.currentWeek
            it[currentDay] = program.currentDay
            it[completedDays] = Json.encodeToJsonElement(
                MapSerializer(Int.serializer(), ListSerializer(Int.serializer())),
                program.completedDays
            )
            it[startedAt] = program.startedAt
            it[updatedAt] = program.updatedAt
            it[finished] = program.finished
        }
        program.id
    }

    fun fetchUserPrograms(userId: UUID): List<UserProgramDTO> = transaction {
        UserProgramTable.select { UserProgramTable.userId eq userId }
            .mapNotNull { row ->
                try {
                    UserProgramDTO(
                        id = row[UserProgramTable.id],
                        userId = row[UserProgramTable.userId],
                        programId = row[UserProgramTable.programId],
                        currentWeek = row[UserProgramTable.currentWeek],
                        currentDay = row[UserProgramTable.currentDay],
                        completedDays = Json.decodeFromJsonElement(row[UserProgramTable.completedDays]),
                        startedAt = row[UserProgramTable.startedAt],
                        updatedAt = row[UserProgramTable.updatedAt],
                        finished = row[UserProgramTable.finished]
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
    }

    fun fetchUserProgramByIds(userId: UUID, programId: UUID): UserProgramDTO? = transaction {
        UserProgramTable.select {
            (UserProgramTable.userId eq userId) and (UserProgramTable.programId eq programId)
        }.mapNotNull { row ->
            try {
                UserProgramDTO(
                    id = row[UserProgramTable.id],
                    userId = row[UserProgramTable.userId],
                    programId = row[UserProgramTable.programId],
                    currentWeek = row[UserProgramTable.currentWeek],
                    currentDay = row[UserProgramTable.currentDay],
                    completedDays = Json.decodeFromJsonElement(row[UserProgramTable.completedDays]),
                    startedAt = row[UserProgramTable.startedAt],
                    updatedAt = row[UserProgramTable.updatedAt],
                    finished = row[UserProgramTable.finished]
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }.singleOrNull()
    }

    fun markProgramFinished(progressId: UUID): Int = transaction {
        UserProgramTable.update({ UserProgramTable.id eq progressId }) {
            it[finished] = true
            it[updatedAt] = Instant.now()
        }
    }

    fun updateCompletedDays(updatedCompletedDays : Map<Int, List<Int>>, newDay: Int, id: UUID): Int {
        return transaction {
            UserProgramTable.update({ UserProgramTable.id eq id }) {
                it[currentDay] = newDay
                it[completedDays] = Json.encodeToJsonElement(
                    MapSerializer(Int.serializer(), ListSerializer(Int.serializer())),
                    updatedCompletedDays
                )
                it[updatedAt] = Instant.now()
            }
        }
    }
}

@Serializable
data class UserProgramDTO(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    @Serializable(with = UUIDSerializer::class) val userId: UUID,
    @Serializable(with = UUIDSerializer::class) val programId: UUID,
    val currentWeek: Int,
    val currentDay: Int,
    val completedDays: Map<Int, List<Int>>,
    val currentTraining: CurrentTrainingDTO? = null,
    @Serializable(with = InstantSerializer::class) val startedAt: Instant,
    @Serializable(with = InstantSerializer::class) val updatedAt: Instant,
    val finished: Boolean
)

@Serializable
data class CurrentTrainingDTO(
    @Serializable(with = UUIDSerializer::class) val programId: UUID,
    val weekIndex: Int,
    val dayIndex: Int,
    val name: String,
    val exercises: List<ExerciseProgressDTO>
)
