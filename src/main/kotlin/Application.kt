package com.kaizen

import com.kaizen.databases.postgresql.Database
import com.kaizen.databases.postgresql.InitializeDatabase
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    Database.getConnection()
    InitializeDatabase
    configureSerialization()
    configureRouting()
}