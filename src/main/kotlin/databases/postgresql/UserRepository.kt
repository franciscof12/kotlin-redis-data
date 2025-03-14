package com.kaizen.databases.postgresql

import com.kaizen.models.User

object UserRepository {
    fun findUserById(id: Int): User {
        val query =
            """
                SELECT * FROM users
                WHERE id = ?
            """.trimIndent()

        return Database.getConnection().use { connection ->
            connection.prepareStatement(query).use { statement ->
                statement.setInt(1, id)

                statement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        User(
                            id = resultSet.getString("id"),
                            name = resultSet.getString("name")
                        )
                    } else {
                        User("", "")
                    }
                }
            }
        }
    }
}

