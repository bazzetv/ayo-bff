package com.terra.bff.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

// Table principale des utilisateurs
object UsersTable : Table("users") {
    val id = uuid("id")
    val email = varchar("email", 255).uniqueIndex()
    val name = varchar("name", 255).nullable()
    val createdAt = timestamp("created_at").default(Instant.now())

    override val primaryKey = PrimaryKey(id)

    fun findOrCreateUserByGoogleId(email: String, googleId: String): UUID {
        return transaction {
            UsersTable.select { UsersTable.email eq email }.singleOrNull()?.get(UsersTable.id)
                ?: run {
                    val newId = UUID.randomUUID()
                    UsersTable.insert {
                        it[id] = newId
                        it[UsersTable.email] = email
                    }
                    AuthGoogleTable.insert {
                        it[userId] = newId
                        it[AuthGoogleTable.googleId] = googleId
                    }
                    newId
                }
        }
    }

    fun findOrCreateUserByAppleId(email: String, appleId: String?): UUID {
        return transaction {
            UsersTable.select { UsersTable.email eq email }.singleOrNull()?.get(UsersTable.id)
                ?: run {
                    val newId = UUID.randomUUID()
                    UsersTable.insert {
                        it[id] = newId
                        it[UsersTable.email] = email
                    }
                    if (appleId != null) {
                        AuthAppleTable.insert {
                            it[userId] = newId
                            it[AuthAppleTable.appleId] = appleId
                        }
                    }
                    newId
                }
        }
    }
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

object AuthAppleTable : Table("auth_apple") {
    val userId = uuid("user_id").references(UsersTable.id)
    val appleId = varchar("apple_id", 255).uniqueIndex()

    override val primaryKey = PrimaryKey(userId)
}