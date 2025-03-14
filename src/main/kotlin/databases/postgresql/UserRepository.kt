package com.kaizen.databases.postgresql

import com.kaizen.models.User
import java.sql.ResultSet

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
                        User(getString(resultSet, "id"), getString(resultSet, "name"))
                    } else {
                        User("", "")
                    }
                }
            }
        }
    }

    private fun getString(resultSet: ResultSet, columnLabel: String) =
        resultSet.getString(columnLabel)
}

