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
    val id = uuid("id").uniqueIndex()
    val requestId = varchar("request_id", 255).uniqueIndex()
    val userId = uuid("user_id").references(UsersTable.id)
    val prompt = text("prompt")
    val model = text("model")
    val numImages = integer("num_images")
    val status = varchar("status", 20).default("pending") // pending, in_progress, done, failed
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    fun createGenerationRequest(requestId: String, userId: UUID, prompt: String, model: String, numImages: Int) {
        transaction {
            // 🔹 Générer un UUID manuellement
            val generatedRequestId = UUID.randomUUID()

            // 🔹 Insérer la requête de génération avec cet UUID
            GenerationRequestsTable.insert {
                it[id] = generatedRequestId // ✅ Utilisation de l'UUID généré
                it[GenerationRequestsTable.requestId] = requestId
                it[GenerationRequestsTable.userId] = userId
                it[GenerationRequestsTable.prompt] = prompt
                it[GenerationRequestsTable.model] = model
                it[GenerationRequestsTable.numImages] = numImages
                it[GenerationRequestsTable.status] = "pending"
            }

            // 🔹 Insérer les images avec le bon UUID
            repeat(numImages) {
                GeneratedImagesTable.insert {
                    it[generationRequestId] = generatedRequestId // ✅ Utiliser l'UUID
                    it[status] = "pending"
                }
            }
        }
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