package com.kaizen.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: String, val name: String) {
    fun isEmpty(): Boolean {
        return id.isEmpty() && name.isEmpty()
    }
}