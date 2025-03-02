package com.terra.bff.database

import ReplicateResponse
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
    val userId = uuid("user_id").references(UsersTable.id)
    val replicateImageId = varchar("replicate_image_id", 255).nullable().uniqueIndex()
    val url = text("url").nullable()
    val status = varchar("status", 50).default("pending")
        .check { it inList listOf("pending", "uploaded", "failed") }
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    @Serializable
    data class GeneratedImageResponse(
        val id: String,
        @Serializable(with = UUIDSerializer::class)
        val generationRequestId: UUID,
        @Serializable(with = UUIDSerializer::class)
        val userId: UUID,
        val replicateImageId: String?,
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
                        GenerationRequestsTable.requestId eq requestId
                    } else {
                        GenerationRequestsTable.userId eq userId
                    }
                }
                .map {
                    GeneratedImageResponse(
                        id = it[GeneratedImagesTable.id].toString(),
                        generationRequestId = it[GeneratedImagesTable.generationRequestId],
                        userId = it[GeneratedImagesTable.userId],
                        replicateImageId = it[GeneratedImagesTable.replicateImageId],
                        prompt = it[GenerationRequestsTable.prompt],
                        status = it[GeneratedImagesTable.status],
                        created_at = it[GeneratedImagesTable.createdAt].toString(),
                        url = it[GeneratedImagesTable.url]
                    )
                }
        }
    }

    fun updateImagesFromReplicateResponse(request: ReplicateResponse) {
        transaction {
            val generationRequestId = GenerationRequestsTable
                .slice(GenerationRequestsTable.id)
                .select { GenerationRequestsTable.requestId eq request.id }
                .singleOrNull()?.get(GenerationRequestsTable.id)

            if (generationRequestId != null) {
                GenerationRequestsTable.update({ GenerationRequestsTable.id eq generationRequestId }) {
                    it[replicateStatus] = request.status
                    if (request.error != null) {
                        it[errorMessage] = request.error
                    }
                }

                if (request.status == "succeeded" && request.output != null) {
                    request.output.forEachIndexed { index, imageUrl ->
                        GeneratedImagesTable.update({ GeneratedImagesTable.generationRequestId eq generationRequestId }) {
                            it[status] = "uploaded"
                            it[url] = imageUrl
                        }
                    }
                }
            } else {
                println("⚠️ Aucun generationRequestId trouvé pour requestId = ${request.id}")
            }
        }
    }
}
