package com.terra.bff.database

import com.terra.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object GeneratedImagesTable : Table("generated_images") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val requestId = varchar("request_id", 255).references(GenerationRequestsTable.requestId)
    val imageUrl = text("image_url").nullable()
    val status = varchar("status", 20).default("pending") // pending, done, failed
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)

    @Serializable
    data class GeneratedImageResponse(
        val id: String,
        val request_id: String,
        val prompt: String,
        val status: String,
        val created_at: String,
        val url: String?
    )

    fun getImagesForUserOrRequest(userId: UUID, requestId: String?): List<GeneratedImageResponse> {
        return transaction {
            (GeneratedImagesTable innerJoin GenerationRequestsTable)
                .select {
                    if (requestId != null)
                        GeneratedImagesTable.requestId eq requestId
                    else
                        GenerationRequestsTable.userId eq userId
                }
                .map {
                    GeneratedImageResponse(
                        id = it[GeneratedImagesTable.id].toString(),
                        request_id = it[GeneratedImagesTable.requestId],
                        prompt = it[GenerationRequestsTable.prompt],
                        status = it[GeneratedImagesTable.status],
                        created_at = it[GeneratedImagesTable.createdAt].toString(),
                        url = it[GeneratedImagesTable.imageUrl]
                    )
                }
        }
    }
}