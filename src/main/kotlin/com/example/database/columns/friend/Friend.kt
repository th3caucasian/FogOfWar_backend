package com.example.database.columns.friend

import com.example.database.columns.user_data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Friend: Table("friend") {
    private val userId = long("user_id").references(UserData.id)
    private val friendId = long("friend_id").references(UserData.id)

    override val primaryKey = PrimaryKey(userId, friendId, name = "PK_friend")

    fun insert(friendDTO: FriendDTO) {
        transaction {
            Friend.insert {
                it[userId] = friendDTO.userId
                it[friendId] = friendDTO.friendId
            }
        }
    }

}