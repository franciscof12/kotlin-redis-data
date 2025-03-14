package com.kaizen.databases.postgresql

import com.kaizen.databases.postgresql.Database.getConnection
import java.sql.Statement

object InitializeDatabase {
    init {
        val sql = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL
            );

            INSERT INTO users (id, name)
            SELECT 1, 'Alice'
            WHERE NOT EXISTS (SELECT 1 FROM users WHERE id = 1);
        """.trimIndent()

        try {
            getConnection().createStatement().use { statement: Statement ->
                statement.execute(sql)
            }
        } catch (e: Exception) {
            throw Exception("Failed to initialize database: ${e.message}")
        }
    }
}

