package com.example.features.add_friend

import com.example.database.columns.friend.Friend
import com.example.database.columns.friend.FriendDTO
import com.example.database.columns.marker.MarkerDTO
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AddFriendController(private val call: ApplicationCall) {

    suspend fun addFriend() {

        try {
            val addFriendReceiveRemote = call.receive<AddFriendReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(addFriendReceiveRemote.userNumber).id!!
            val userIdToAdd = UserData.fetchUserByNumber(addFriendReceiveRemote.userNumberToAdd).id!!


            Friend.insert(FriendDTO(
                userId = currentUserId,
                friendId = userIdToAdd
                )
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }



    }
}