package com.ayo.bff.database

import com.ayo.bff.utils.JsonElementColumnType
import com.ayo.bff.utils.TextArrayColumnType
import com.ayo.bff.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

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
object ProgramTable : Table("program") {
    val id = uuid("id").autoGenerate()
    val title = varchar("title", 255)
    val description = text("description")
    val durationWeeks = integer("duration_weeks")
    val daysPerWeek = integer("days_per_week")
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

    fun fetchAllPublishedPrograms(): List<ProgramPreviewDto> {
        return transaction {
            ProgramTable
                .select { isPublished eq true }
                .map {
                    ProgramPreviewDto(
                        id = it[ProgramTable.id],
                        title = it[title],
                        description = it[description],
                        durationWeeks = it[durationWeeks],
                        daysPerWeek = it[daysPerWeek],
                        level = it[level],
                        category = it[category],
                        goal = it[goal],
                        coachName = it[coachName],
                        imageUrl = it[imageUrl],
                        tags = it[tags] ?: emptyList()
                    )
                }
        }
    }
}

@Serializable
data class ProgramPreviewDto(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val title: String,
    val description: String,
    val durationWeeks: Int,
    val daysPerWeek: Int,
    val level: Level,
    val category: Category,
    val goal: String?,
    val coachName: String?,
    val imageUrl: String,
    val tags: List<String> = emptyList()
)
