package com.kaizen.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val id: String = "") {
    fun isEmpty(): Boolean = id.isBlank() && name.isBlank()
}