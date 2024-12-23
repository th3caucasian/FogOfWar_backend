package com.example.database.columns.friend

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.user_data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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

    fun delete(currentUserId: Long, currentFriendId: Long) {
        transaction {
            Friend.deleteWhere { ((Friend.userId eq currentUserId) and (Friend.friendId eq currentFriendId)) and  ((Friend.friendId eq currentUserId) and (Friend.userId eq currentFriendId))}
        }
    }

    fun getFriends(currentUserId : Long) : List<String> {
        val friends = mutableListOf<String>()
        try {
            transaction {
                val f1 = Friend.alias("f1")
                val f2 = Friend.alias("f2")
                val udf = UserData.alias("udf")

                exec("""
                    SELECT DISTINCT udf.phone_number 
                    FROM friend f1 
                    INNER JOIN friend f2 ON f1.friend_id = f2.user_id
                    INNER JOIN user_data udf ON f1.friend_id = udf.id 
                    WHERE f1.user_id = $currentUserId AND f2.friend_id = $currentUserId
                        """
                ) { rs ->
                    while (rs.next()) {
                        friends += rs.getString("phone_number")
                    }

                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return friends
    }

}