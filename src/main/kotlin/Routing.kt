package com.kaizen

import com.kaizen.routes.user
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        user()
    }
}
