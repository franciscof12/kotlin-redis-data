package com.kaizen.databases.postgresql

import com.kaizen.models.User
import java.sql.ResultSet

object UserRepository {
    fun findUserById(id: Int): User? {
        val query = "SELECT * FROM users WHERE id = ?"

        return try {
            Database.getConnection().use { connection ->
                connection.prepareStatement(query).use { statement ->
                    statement.setInt(1, id)

                    statement.executeQuery().use { resultSet ->
                        if (resultSet.next()) {
                            User(resultSet.getString("name"), resultSet.getInt("id"))
                        } else {
                            null
                        }
                    }
                }
            }
        } catch (e: Exception) {
            null
        }
    }


    fun createUser(user: User): User? {
        val query = "INSERT INTO users (name) VALUES (?) RETURNING id"

        return try {
            Database.getConnection().use { connection ->
                connection.prepareStatement(query).use { statement ->
                    statement.setString(1, user.name)

                    statement.executeQuery().use { resultSet ->
                        if (resultSet.next()) {
                            User(user.name, resultSet.getInt("id"))
                        } else {
                            null
                        }
                    }
                }
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun getString(resultSet: ResultSet, columnLabel: String) =
        resultSet.getString(columnLabel)
}

