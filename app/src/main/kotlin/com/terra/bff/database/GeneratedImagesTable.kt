package com.terra.bff.database

import com.terra.bff.routes.ImageUpdate
import com.terra.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*

object GeneratedImagesTable : Table("generated_images") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val generationRequestId = uuid("generation_request_id").references(GenerationRequestsTable.id)
    val url = text("url").nullable()
    val status = varchar("status", 20).default("pending") // pending, done, failed
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    @Serializable
    data class GeneratedImageResponse(
        val id: String,
        @Serializable(with = UUIDSerializer::class)
        val generationRequestId: UUID,
        val prompt: String,
        val status: String,
        val created_at: String,
        val url: String?
    )

    fun getImagesForUserOrRequest(userId: UUID, requestId: String?): List<GeneratedImageResponse> {
        return transaction {
            (GeneratedImagesTable innerJoin GenerationRequestsTable)
                .select {
                    if (requestId != null) {
                        GenerationRequestsTable.requestId eq requestId // ✅ Correction ici
                    } else {
                        GenerationRequestsTable.userId eq userId
                    }
                }
                .map {
                    GeneratedImageResponse(
                        id = it[GeneratedImagesTable.id].toString(),
                        generationRequestId = it[GeneratedImagesTable.generationRequestId],
                        prompt = it[GenerationRequestsTable.prompt],
                        status = it[GeneratedImagesTable.status],
                        created_at = it[GeneratedImagesTable.createdAt].toString(),
                        url = it[GeneratedImagesTable.url]
                    )
                }
        }
    }

    fun updateImagesStatus(requestId: String, images: List<ImageUpdate>) {
        transaction {
            val existingImages = (GeneratedImagesTable innerJoin GenerationRequestsTable)
                .select { GenerationRequestsTable.requestId eq requestId }
                .orderBy(GeneratedImagesTable.id)
                .map { it[GeneratedImagesTable.id] }

            if (existingImages.size != images.size) {
                throw IllegalStateException("Nombre d'images reçues (${images.size}) ne correspond pas à celles en BDD (${existingImages.size})")
            }

            existingImages.zip(images).forEach { (imageId, newData) ->
                GeneratedImagesTable.update({ GeneratedImagesTable.id eq imageId }) {
                    it[status] = newData.status
                    if (newData.url != null) {
                        it[url] = newData.url
                    }
                }
            }
        }
    }
}