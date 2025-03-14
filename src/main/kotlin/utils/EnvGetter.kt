package com.kaizen.utils

fun getEnv(env: String): String? {
    if (System.getenv(env) == null) {
        throw Exception("Environment variable $env is not set")
    }
    val envValue = System.getenv(env)
    return envValue
}