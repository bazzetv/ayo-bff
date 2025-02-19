package com.terra.bff.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DatabaseFactory {
    private val config = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/terraai"
        driverClassName = "org.postgresql.Driver"
        username = "postgres"
        password = "password"
        maximumPoolSize = 10
    }

    private val dataSource = HikariDataSource(config)

    fun init() {
        Database.connect(dataSource)
        transaction {
            SchemaUtils.create(UsersTable, AuthGoogleTable, AuthPasswordTable)
        }
    }
}