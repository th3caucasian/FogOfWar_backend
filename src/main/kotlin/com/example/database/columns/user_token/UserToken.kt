package com.example.database.columns.user_token

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


object UserToken: Table("user_token") {
    val id = UserToken.long("id").autoIncrement()
    private val phoneNumber = UserToken.varchar("phone_number", 15)
    private val token = UserToken.varchar("token", 50)

    override val primaryKey = PrimaryKey(id, name = "PK_user_token")

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            UserToken.insert {
                it[phoneNumber] = tokenDTO.phoneNumber
                it[token] = tokenDTO.token
            }
        }
    }

}