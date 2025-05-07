package com.ayo.bff.database

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.datetime
import org.postgresql.util.PGobject

enum class Sex { MALE, FEMALE }
enum class TargetMuscle {
    CHEST, BACK, LEGS, SHOULDERS, BICEPS, TRICEPS,
    CORE, GLUTES, CALVES, FULL_BODY
}
enum class Equipment {
    BODYWEIGHT, DUMBBELL, BARBELL, KETTLEBELL,
    MACHINE, CABLE, BAND, OTHER
}
enum class Category {
    BODYBUILDING, POWERBUILDING, BODYSHAPE, FUNCTIONAL, ENDURANCE
}
enum class Level {
    BEGINNER, INTERMEDIATE, ADVANCED
}

// Table principale program
object Program : Table("program") {
    val id = uuid("id").autoGenerate()
    val title = varchar("title", 255)
    val description = text("description")
    val durationWeeks = integer("duration_weeks")
    val daysPerWeek = integer("days_per_week")
    val sex = registerColumn<List<String>>("sex", TextArrayColumnType())
    val level = customEnumeration("level", "level", { Level.valueOf(it as String) }, { it.name })
    val category = customEnumeration("category", "category", { Category.valueOf(it as String) }, { it.name })
    val goal = text("goal").nullable()
    val coachName = varchar("coach_name", 255).nullable()
    val structure = registerColumn<JsonElement>("structure", JsonElementColumnType())
    val imageUrl = varchar("image_url", 512)
    val tags = registerColumn<List<String>>("tags", TextArrayColumnType()).nullable()
    val isPublished = bool("is_published").default(false)
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}

// JSONB handler
class JsonElementColumnType : ColumnType() {
    override fun sqlType(): String = "JSONB"

    override fun valueFromDB(value: Any): JsonElement = when (value) {
        is PGobject -> Json.decodeFromString(JsonElement.serializer(), value.value ?: "{}")
        is String -> Json.decodeFromString(JsonElement.serializer(), value)
        else -> error("Impossible de convertir en JsonElement : $value")
    }

    override fun notNullValueToDB(value: Any): Any {
        return PGobject().apply {
            type = "jsonb"
            this.value = Json.encodeToString(JsonElement.serializer(), value as JsonElement)
        }
    }
}

// TEXT[] handler
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