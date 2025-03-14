package com.kaizen.controllers

import com.kaizen.databases.postgresql.UserRepository
import com.kaizen.databases.redis.RedisClient
import com.kaizen.models.User

object UserController {
    fun getUserById(id: Int): User? {
        return try {
            RedisClient.getUser(id) ?: UserRepository.findUserById(id)?.apply {
                RedisClient.saveUser(this)
            }
        } catch (e: Exception) {
            null
        }
    }

    fun createUser(requestBody: Map<String, String>) {
        val name = requestBody["name"]
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("Name is required")
        }

        val userCreated = UserRepository.createUser(User(name))

        if (userCreated != null) {
            RedisClient.saveUser(userCreated)
        }
    }
}