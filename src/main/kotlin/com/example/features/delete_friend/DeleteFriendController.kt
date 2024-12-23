package com.example.features.delete_friend

import com.example.database.columns.friend.Friend
import com.example.database.columns.marker.Marker
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DeleteFriendController(private val call: ApplicationCall) {

    suspend fun deleteFriend() {

        try {
            val deleteFriendReceiveRemote = call.receive<DeleteFriendReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(deleteFriendReceiveRemote.phoneNumber).id!!
            val currentFriendId = UserData.fetchUserByNumber(deleteFriendReceiveRemote.phoneNumber).id!!

            Friend.delete(currentUserId, currentFriendId)

        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}