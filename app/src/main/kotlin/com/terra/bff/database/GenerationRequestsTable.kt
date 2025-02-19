package com.terra.bff.database

import com.terra.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object GenerationRequestsTable : Table("generation_requests") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val requestId = varchar("request_id", 255).uniqueIndex()
    val userId = uuid("user_id").references(UsersTable.id)
    val prompt = text("prompt")
    val model = text("model")
    val status = varchar("status", 20).default("pending") // pending, in_progress, done, failed
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    fun createGenerationRequest(userId: UUID, prompt: String, model: String, numImages: Int): String {
        val requestId = "gen_${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(8)}"

        transaction {
            GenerationRequestsTable.insert {
                it[GenerationRequestsTable.requestId] = requestId
                it[GenerationRequestsTable.userId] = userId
                it[GenerationRequestsTable.prompt] = prompt
                it[GenerationRequestsTable.model] = model
                it[GenerationRequestsTable.status] = "pending"
            }

            repeat(numImages) {
                GeneratedImagesTable.insert {
                    it[GeneratedImagesTable.requestId] = requestId
                    it[GeneratedImagesTable.status] = "pending"
                }
            }
        }
        return requestId
    }
}



@Serializable
data class GenerationRequest(
    val requestId: String,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
    val prompt: String,
    val status: String,
    val createdAt: String
)