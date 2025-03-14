package com.kaizen.routes

import com.kaizen.databases.postgresql.UserRepository
import com.kaizen.models.User
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.user() {
    get("/user/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val user = handleGetUser(id)

        if (user.isEmpty()) {
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "user not found"))
        }

        call.respond(user)
    }
}

fun handleGetUser(id: Int): User {
    return UserRepository.findUserById(id)
}