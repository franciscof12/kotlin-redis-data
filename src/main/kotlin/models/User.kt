package com.kaizen.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val id: Int = 0) {
    fun isEmpty(): Boolean = name.isBlank() && id == 0
}