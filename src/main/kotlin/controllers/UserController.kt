package com.kaizen.controllers

import com.kaizen.databases.postgresql.UserRepository
import com.kaizen.databases.redis.RedisClient
import com.kaizen.models.User

object UserController {
    fun getUserById(id: Int): User? {
        val redisCachedUser = RedisClient.getUser(id)
        if (redisCachedUser != null) {
            return redisCachedUser
        }

        val user = UserRepository.findUserById(id)
        if (user != null) {
            RedisClient.saveUser(user)
        }

        return user
    }

    fun createUser(requestBody: Map<String, String>) {
        val name = requestBody["name"] ?: ""
        val userCreated = UserRepository.createUser(User(name))

        if (userCreated != null) {
            RedisClient.saveUser(userCreated)
        }
    }
}