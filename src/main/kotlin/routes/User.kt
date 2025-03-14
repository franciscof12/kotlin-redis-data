package com.kaizen.routes

import com.kaizen.controllers.UserController
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Route.user() {
    get("/user/{id}") {
        val id = call.parameters["id"]?.toIntOrNull() ?: 0
        val user = UserController.getUserById(id)

        if (user == null) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid user ID"))
        }

        call.respond(HttpStatusCode.OK, mapOf("user" to user))
    }

    post("/user") {
        val body = call.receive<Map<String, String>>()
        val user = UserController.createUser(body)

        call.respond(HttpStatusCode.Created, mapOf("message" to "User created successfully"))
    }
}