package com.kaizen.routes

import com.kaizen.controllers.UserController
import io.ktor.http.HttpStatusCode
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
            val user = UserController.getUserById(id)

            if (user == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid user ID"))
            }

            call.respond(HttpStatusCode.OK, mapOf("user" to user))
        }

        post {
            val body = call.receive<Map<String, String>>()

            val user = try {
                UserController.createUser(body)
                call.respond(HttpStatusCode.Created, mapOf("message" to "User created successfully"))
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Internal server error"))
            }
        }
    }
}