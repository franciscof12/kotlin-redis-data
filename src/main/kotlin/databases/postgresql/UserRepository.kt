package com.kaizen.databases.postgresql

import com.kaizen.controllers.UserController
import com.kaizen.models.User
import java.sql.ResultSet

object UserRepository {
    fun findUserById(id: String): User {
        val query =
            """
                SELECT * FROM users
                WHERE id = ?
            """.trimIndent()

        return Database.getConnection().use { connection ->
            connection.prepareStatement(query).use { statement ->
                statement.setInt(1, id.toInt())

                statement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        User(getString(resultSet, "name"), getString(resultSet, "id"))
                    } else {
                        User("", "")
                    }
                }
            }
        }
    }

    fun createUser(user: User): User {
        val query =
            """
                INSERT INTO users (name)
                VALUES (?)
                RETURNING id
            """.trimIndent()


        return Database.getConnection().use { connection ->
            connection.prepareStatement(query).use { statement ->
                statement.setString(1, user.name)

                statement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        User(user.name, getString(resultSet, "id"))
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

