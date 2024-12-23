package com.example.database.columns.user_data

import com.example.database.column_types.Point
import com.example.database.column_types.PointArrayColumnType
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object UserData: Table("user_data") {
    val id = long("id").autoIncrement()
    private val login = varchar("login", 25).uniqueIndex("UI_UserModel_login").nullable()
    private val password = varchar("password", 50)
    val phoneNumber = varchar("phone_number", 15).uniqueIndex("UI_user_data_phone_number")
    private val clearedPoints = registerColumn("cleared_points", PointArrayColumnType()).nullable()

    override val primaryKey = PrimaryKey(id, name = "PK_user_data")

    fun insert(userDTO: UserDTO) {
        transaction {
            UserData.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[phoneNumber] = userDTO.phoneNumber
                it[clearedPoints] = userDTO.clearedPoints
            }
        }
    }

    fun fetchUserByNumber(searchNumber: String): UserDTO {
        return transaction {
            val userData = UserData.selectAll().where(phoneNumber eq searchNumber).single()
            UserDTO(
                id = userData[UserData.id],
                login = userData[login],
                password = userData[password],
                phoneNumber = userData[phoneNumber],
                clearedPoints = userData[clearedPoints]
            )
        }
    }


    fun addClearedPoints(phoneNumber: String, points: List<Point>): Boolean {
        return try {
            val pointArrayColumnType = PointArrayColumnType()
            val pointsAsSqlArray = pointArrayColumnType.notNullValueToDB(points)
            transaction {
                exec("""
            UPDATE user_data
            SET cleared_points = cleared_points || ${pointsAsSqlArray}
            WHERE phone_number = '${phoneNumber}'
        """)
            }
            true
        }
        catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getUserPoints(userNumber: String): List<Point>? {
        return try {
            transaction {
                val queryResult = UserData.select(clearedPoints).where(phoneNumber eq userNumber).single()
                val userPoints = queryResult[clearedPoints]
                userPoints
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}