package com.terra.bff.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

// Table principale des utilisateurs
object UsersTable : Table("users") {
    val id = uuid("id")
    val email = varchar("email", 255).uniqueIndex()
    val name = varchar("name", 255).nullable()
    val createdAt = timestamp("created_at").default(Instant.now())

    override val primaryKey = PrimaryKey(id)
}

// Table pour les utilisateurs Google
object AuthGoogleTable : Table("auth_google") {
    val userId = uuid("user_id").references(UsersTable.id)
    val googleId = varchar("google_id", 255).uniqueIndex()

    override val primaryKey = PrimaryKey(userId)
}

// Table pour les utilisateurs classiques (email + mot de passe)
object AuthPasswordTable : Table("auth_password") {
    val userId = uuid("user_id").references(UsersTable.id)
    val passwordHash = varchar("password_hash", 255)

    override val primaryKey = PrimaryKey(userId)
}