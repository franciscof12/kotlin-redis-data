package com.kaizen.routes

import com.kaizen.controllers.UserController.createUser
import com.kaizen.controllers.UserController.getUserById
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.user() {
    route("/user") {
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            val user = getUserById(id)

            if (user == null) {
                call.respond(BadRequest, mapOf("error" to "Invalid user ID"))
            }

            call.respond(OK, mapOf("user" to user))
        }

        post {
            val body = call.receive<Map<String, String>>()

            try {
                createUser(body)
                call.respond(Created, mapOf("message" to "User created successfully"))
            } catch (e: IllegalArgumentException) {
                call.respond(BadRequest, mapOf("error" to e.message))
            } catch (e: Exception) {
                call.respond(InternalServerError, mapOf("error" to "Internal server error"))
            }
        }
    }
}