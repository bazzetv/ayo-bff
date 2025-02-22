package com.terra.bff.database

import org.jetbrains.exposed.sql.Database
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway

object DatabaseFactory {
    private val config = HikariConfig().apply {
        jdbcUrl = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/terraai"
        driverClassName = "org.postgresql.Driver"
        username = System.getenv("DATABASE_USER") ?: "postgres"
        password = System.getenv("DATABASE_PASSWORD") ?: "password"
        maximumPoolSize = 10
    }

    private val dataSource = HikariDataSource(config)

    fun init() {
        // ✅ Connexion à la base de données
        Database.connect(dataSource)

        Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .baselineOnMigrate(true)
            .load()
            .migrate()
    }
}