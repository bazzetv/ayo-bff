package com.ayo.bff.database

import com.ayo.bff.utils.JsonElementColumnType
import com.ayo.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
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

    fun addProgramToUser(userId: UUID, newProgress: ProgressProgramDTO) {
        transaction {
            val existing = ProgressTable
                .select { accountId eq userId }
                .firstOrNull()

            if (existing != null) {
                val currentProgress = Json.decodeFromJsonElement<List<ProgressProgramDTO>>(existing[ProgressTable.programs])
                val updatedList = currentProgress + newProgress
                ProgressTable.update({ accountId eq userId }) {
                    it[programs] = Json.encodeToJsonElement(updatedList)
                    it[updatedAt] = Instant.now()
                }
            } else {
                ProgressTable.insert {
                    it[accountId] = userId
                    it[programs] = Json.encodeToJsonElement(listOf(newProgress))
                    it[createdAt] = Instant.now()
                    it[updatedAt] = Instant.now()
                }
            }
        }
    }

    fun fetchUserProgress(userId: UUID): List<ProgressProgramDTO> {
        return transaction {
            ProgressTable
                .select { accountId eq userId }
                .mapNotNull { row ->
                    try {
                        val json = row[programs]
                        Json.decodeFromJsonElement<List<ProgressProgramDTO>>(json)
                    } catch (e: Exception) {
                        // Log optionnel : e.message
                        null
                    }
                }
                .flatten()
        }
    }



}

@Serializable
data class ProgressProgramDTO(
    @Serializable(with = UUIDSerializer::class)
    val programId: UUID,
    val currentWeek: Int,
    val currentDay: Int,
    val finished: Boolean = false,
    val completedDays: Map<Int, List<Int>> = emptyMap()
)