package com.ayo.bff.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

object AccountTable : Table("account") {
    val id = uuid("id").uniqueIndex()
    val email = varchar("email", 255).uniqueIndex()
    val name = varchar("name", 255).nullable()
    val createdAt = timestamp("created_at").default(Instant.now())

    override val primaryKey = PrimaryKey(id)

    fun findOrCreateAccountByGoogleId(email: String, googleId: String): UUID {
        return transaction {
            AccountTable.select { AccountTable.email eq email }.singleOrNull()?.get(AccountTable.id)
                ?: run {
                    val newId = UUID.randomUUID()
                    AccountTable.insert {
                        it[id] = newId
                        it[AccountTable.email] = email
                    }
                    AuthGoogleTable.insert {
                        it[accountId] = newId
                        it[AuthGoogleTable.googleId] = googleId
                    }
                    newId
                }
        }
    }

    fun findOrCreateAccountByAppleId(email: String, appleId: String?): UUID {
        return transaction {
            AccountTable.select { AccountTable.email eq email }.singleOrNull()?.get(AccountTable.id)
                ?: run {
                    val newId = UUID.randomUUID()
                    AccountTable.insert {
                        it[id] = newId
                        it[AccountTable.email] = email
                    }
                    if (appleId != null) {
                        AuthAppleTable.insert {
                            it[accountId] = newId
                            it[AuthAppleTable.appleId] = appleId
                        }
                    }
                    newId
                }
        }
    }
}

object AuthGoogleTable : Table("auth_google") {
    val accountId = uuid("account_id").references(AccountTable.id)
    val googleId = varchar("google_id", 255).uniqueIndex()

    override val primaryKey = PrimaryKey(accountId)
}

object AuthPasswordTable : Table("auth_password") {
    val accountId = uuid("account_id").references(AccountTable.id)
    val passwordHash = varchar("password_hash", 255)

    override val primaryKey = PrimaryKey(accountId)
}

object AuthAppleTable : Table("auth_apple") {
    val accountId = uuid("account_id").references(AccountTable.id)
    val appleId = varchar("apple_id", 255).uniqueIndex()

    override val primaryKey = PrimaryKey(accountId)
}