package com.kaizen.databases.postgresql

import com.kaizen.databases.postgresql.Database.getConnection
import com.kaizen.models.User
import java.sql.ResultSet

object UserRepository {
    fun selectUserById(id: Int): User? {
        val query = "SELECT * FROM users WHERE id = ?"

        return try {
            getConnection().use { connection ->
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


    fun insertUser(user: User): User? {
        val query = "INSERT INTO users (name) VALUES (?) RETURNING id"

        return try {
            getConnection().use { connection ->
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

