package com.kaizen.routes

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.user() {
    get("/user") {
        call.respondText("User route")
    }
}