package com.ayo.bff.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Progress : Table("progress") {
    val id = uuid("id").autoGenerate()
    val userId = uuid("user_id")
    val programId = uuid("program_id") references Program.id
    val currentWeek = integer("current_week")
    val currentDay = integer("current_day")
    val startedAt = datetime("started_at")
    val finishedAt = datetime("finished_at").nullable()

    override val primaryKey = PrimaryKey(id)
}