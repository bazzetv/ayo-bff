package com.ayo.bff.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Exercise : Table("exercise") {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", 255)
    val targetMuscle = customEnumeration("target_muscle", "TEXT", { TargetMuscle.valueOf(it as String) }, { it.name })
    val equipment = customEnumeration("equipment", "TEXT", { Equipment.valueOf(it as String) }, { it.name }).nullable()
    val description = text("description").nullable()
    val videoUrl = varchar("video_url", 512).nullable()
    val imageUrl = varchar("image_url", 512).nullable()
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}