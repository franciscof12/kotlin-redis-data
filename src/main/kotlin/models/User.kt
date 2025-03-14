package com.kaizen.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val id: String? = null) {
    fun isEmpty(): Boolean {
        return id?.isEmpty() == true && name.isEmpty()
    }
}