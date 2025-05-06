package com.ayo.bff.database

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.postgresql.util.PGobject

enum class Sex {
    MALE,
    FEMALE
}

enum class TargetMuscle {
    CHEST,
    BACK,
    LEGS,
    SHOULDERS,
    BICEPS,
    TRICEPS,
    CORE,
    GLUTES,
    CALVES,
    FULL_BODY
}

enum class Equipment {
    BODYWEIGHT,
    DUMBBELL,
    BARBELL,
    KETTLEBELL,
    MACHINE,
    CABLE,
    BAND,
    OTHER
}

object Program : Table("program") {
    val id = uuid("id").autoGenerate()
    val title = varchar("title", 255)
    val description = text("description")
    val durationWeeks = integer("duration_weeks")
    val sex = registerColumn<List<String>>("sex", TextArrayColumnType())
    val structure = registerColumn<JsonElement>("structure", JsonElementColumnType())
    val imageUrl = varchar("image_url", 512)
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
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

class TextArrayColumnType : ColumnType() {
    override fun sqlType(): String = "TEXT[]"

    override fun valueFromDB(value: Any): List<String> = when (value) {
        is PGobject -> value.value?.removeSurrounding("{", "}")?.split(",") ?: emptyList()
        is String -> value.removeSurrounding("{", "}").split(",")
        is java.sql.Array -> (value.array as Array<*>).filterIsInstance<String>()
        else -> error("Unsupported TEXT[] value: $value")
    }

    override fun notNullValueToDB(value: Any): Any {
        val list = (value as List<*>).joinToString(",", "{", "}") { it.toString() }
        return PGobject().apply {
            type = "text[]"
            this.value = list
        }
    }
}