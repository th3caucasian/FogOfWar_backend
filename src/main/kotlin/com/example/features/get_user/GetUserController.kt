package com.example.features.get_user

import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GetUserController(private val call: ApplicationCall) {

    suspend fun getUser() {

        val getUserReceiveRemote = call.receive<GetUserReceiveRemote>()


        try {
            val userDTO = UserData.fetchUserByNumber(getUserReceiveRemote.phoneNumber)
            if (userDTO == null)
                call.respond(HttpStatusCode.BadRequest)
            call.respond(HttpStatusCode.OK)
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}