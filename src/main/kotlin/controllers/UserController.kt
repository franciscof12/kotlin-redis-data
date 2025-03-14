package com.kaizen.controllers

import com.kaizen.databases.postgresql.UserRepository
import com.kaizen.databases.redis.RedisClient
import com.kaizen.models.User

object UserController {
    fun getUserById(id: String): User {
        val redisCachedUser = RedisClient.getUser(id)
        if (redisCachedUser != null) {
            return redisCachedUser
        }

        val user = UserRepository.findUserById(id.toInt())
        if (!user.isEmpty()) {
            RedisClient.saveUser(user)
        }

        return user
    }
}