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
        val id = call.parameters["id"]!!
        val user = UserController.getUserById(id)

        if (user.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid user ID"))
        }

        call.respond(user)
    }

    post("/user") {
        val body = call.receive<Map<String, String>>()
        val user = UserController.createUser(body)

        call.respond(HttpStatusCode.Created, mapOf("message" to "User created successfully"))
    }
}