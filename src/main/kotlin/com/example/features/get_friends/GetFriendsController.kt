package com.example.features.get_friends

import com.example.database.columns.friend.Friend
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GetFriendsController(private val call: ApplicationCall) {

    suspend fun getFriends() {

        val getFriendsReceiveRemote = call.receive<GetFriendsReceiveRemote>()


        try {
            val userId = UserData.fetchUserByNumber(getFriendsReceiveRemote.phoneNumber).id!!
            val friends = Friend.getFriends(userId)

            call.respond(GetFriendsResponseRemote(friends))
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}