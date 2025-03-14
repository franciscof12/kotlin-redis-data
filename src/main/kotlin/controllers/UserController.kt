package com.kaizen.controllers

import com.kaizen.databases.postgresql.UserRepository.insertUser
import com.kaizen.databases.postgresql.UserRepository.selectUserById
import com.kaizen.databases.redis.RedisClient
import com.kaizen.models.User

object UserController {
    fun getUserById(id: Int): User? {
        return try {
            RedisClient.getUser(id) ?: selectUserById(id)?.apply {
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

        val userCreated = insertUser(User(name))

        if (userCreated != null) {
            RedisClient.saveUser(userCreated)
        }
    }
}