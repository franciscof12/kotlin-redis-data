package com.kaizen.routes

import com.kaizen.controllers.UserController
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.user() {
    get("/user/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val user = UserController.getUserById(id)

        if (user.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid user ID"))
        }

        call.respond(user)
    }
}