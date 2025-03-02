package com.terra.bff.database

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.postgresql.util.PGobject

object ModelsTable : Table("models") {
    val id = uuid("id").uniqueIndex().autoGenerate()
    val name = varchar("name", 255).uniqueIndex()
    val apiPath = text("api_path")
    val description = text("description")
    val imageUrl = text("image_url")
    val version = text("version").nullable()
    val parameters = registerColumn<JsonElement>("parameters", JsonElementColumnType())
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    fun getModelConfig(name: String): Map<String, Any>? {
        return transaction {
            val model = ModelsTable.select { ModelsTable.name eq name }.singleOrNull() ?: return@transaction null
            val parameters: JsonElement = model[ModelsTable.parameters]
            val version: String? = model[ModelsTable.version]?.takeIf { it.isNotBlank() } // Assurer un String? propre

            mapOf(
                "id" to model[ModelsTable.id],
                "name" to model[ModelsTable.name],
                "apiPath" to model[ModelsTable.apiPath],
                "parameters" to parameters,
            ) + (version?.let { mapOf("version" to it) } ?: emptyMap())
        }
    }
}

class JsonElementColumnType : ColumnType() {
    override fun sqlType(): String = "JSONB"

    override fun valueFromDB(value: Any): JsonElement = when (value) {
        is PGobject -> Json.decodeFromString(JsonElement.serializer(), value.value ?: "{}")
        is String -> Json.decodeFromString(JsonElement.serializer(), value)
        else -> throw IllegalArgumentException("Impossible de convertir en JsonElement : $value")
    }

    override fun notNullValueToDB(value: Any): Any {
        val pgObject = PGobject()
        pgObject.type = "jsonb"
        pgObject.value = Json.encodeToString(JsonElement.serializer(), value as JsonElement)

        return pgObject
    }
}