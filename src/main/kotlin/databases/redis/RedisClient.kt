package com.kaizen.databases.redis

import com.kaizen.models.User
import com.kaizen.utils.getEnv
import io.lettuce.core.RedisClient
import kotlinx.serialization.json.Json

private val REDIS_URL = getEnv("REDIS_URL")

object RedisClient {
    private val redisClient = RedisClient.create(REDIS_URL)
    private val connection = redisClient.connect()
    private val command = connection.sync()

    fun getUser(id: Int): User? {
        val encodedUser = command.get("user:$id")
        return encodedUser?.let { Json.decodeFromString<User>(it) }
    }

    fun saveUser(user: User) {
        val encodedUser = Json.encodeToString(user)

        command.setex("user:${user.id}", 86400, encodedUser)
    }
}