package com.kaizen.databases.postgresql

import com.kaizen.utils.getEnv
import java.sql.Connection
import java.sql.DriverManager


object Database {
    private val URL = getEnv("DATABASE_URL")
    private val USER = getEnv("DATABASE_USER")
    private val PASSWORD = getEnv("DATABASE_PASSWORD")


    fun getConnection(): Connection {
        return try {
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Failed to connect to the database", e)
        }
    }
}

