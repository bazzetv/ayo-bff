package com.ayo.bff.database

import com.ayo.bff.utils.JsonElementColumnType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

object ProgressTable : Table("progress") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val accountId = uuid("account_id").references(AccountTable.id)
    val programs = registerColumn<JsonElement>("programs", JsonElementColumnType())
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())

    override val primaryKey = PrimaryKey(id)

    fun fetchProgramsStatusByUser(accountId: UUID): List<Map<String, Any>> {
        return transaction {
            ProgressTable
                .select { ProgressTable.accountId eq accountId }
                .mapNotNull { row ->
                    val programsJson = row[programs]
                    programsJson.jsonArray.mapNotNull { programElement ->
                        val obj = programElement.jsonObject
                        mapOf(
                            "title" to (obj["title"]?.jsonPrimitive?.content ?: "Unknown"),
                            "finished" to (obj["finished"]?.jsonPrimitive?.boolean ?: false)
                        )

                    }
                }
                .flatten()
        }
    }
}

@Serializable
data class ProgressProgramDTO(
    val programId: String,
    val currentWeek: Int,
    val currentDay: String,
    val weeks: List<WeekProgressDTO>,
    val finished: Boolean = false
)

@Serializable
data class WeekProgressDTO(
    val week: Int,
    val days: List<DayProgressDTO>
)

@Serializable
data class DayProgressDTO(
    val dayName: String,
    val finished: Boolean = false,
    val exercises: List<ExerciseProgressDTO>
)


@Serializable
data class ExerciseProgressDTO(
    val name: String,
    val equipment: String,
    val sets: String,
    val reps: String,
    val rest: Int,
    val substitutions: List<String> = emptyList(),
    val notes: String,
    val finished: Boolean = false,
    val weights: List<Float?> = emptyList()
)