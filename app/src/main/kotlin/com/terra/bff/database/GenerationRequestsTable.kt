package com.terra.bff.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object GenerationRequestsTable : Table("generation_requests") {
    val id = uuid("id").uniqueIndex()
    val requestId = varchar("request_id", 255).uniqueIndex()
    val userId = uuid("user_id").references(UsersTable.id)
    val prompt = text("prompt")
    val model = text("model")
    val numImages = integer("num_images").check { it greater 0 }
    val status = varchar("status", 50).default("starting")
        .check { it inList listOf("starting", "processing", "succeeded", "failed") }
    val errorMessage = text("error_message").nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    fun createGenerationRequest(requestId: String, userId: UUID, prompt: String, model: String, numImages: Int) {
        transaction {
            val generatedRequestId = UUID.randomUUID()

            transaction {
                // 🔹 Insérer la requête de génération avec l'UUID généré
                GenerationRequestsTable.insert {
                    it[id] = generatedRequestId
                    it[GenerationRequestsTable.requestId] = requestId
                    it[GenerationRequestsTable.userId] = userId
                    it[GenerationRequestsTable.prompt] = prompt
                    it[GenerationRequestsTable.model] = model
                    it[GenerationRequestsTable.numImages] = numImages
                    it[GenerationRequestsTable.status] = "starting"
                }

                // 🔹 Insérer les images avec le bon requestId
                repeat(numImages) {
                    GeneratedImagesTable.insert {
                        it[generationRequestId] = generatedRequestId
                        it[GeneratedImagesTable.userId] = userId
                        it[status] = "pending"
                    }
                }
            }
        }
    }
}
