package com.kaizen.controllers

import com.kaizen.databases.postgresql.UserRepository
import com.kaizen.models.User

object UserController {
    fun getUserById(id: Int): User {
        return UserRepository.findUserById(id)
    }
}