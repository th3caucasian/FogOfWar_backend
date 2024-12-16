package com.example.features.login

import com.example.database.columns.user_token.UserToken
import com.example.database.columns.user_token.TokenDTO
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = UserData.fetchUserByNumber(loginReceiveRemote.phoneNumber)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "No user found")
        }
        else {
            if (userDTO.password == loginReceiveRemote.password) {
                val token = UUID.randomUUID().toString()
                UserToken.insert(
                    TokenDTO(
                        id = 0,
                        phoneNumber = loginReceiveRemote.phoneNumber,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            }
            else
            {
                call.respond(HttpStatusCode.BadRequest, "Passwords do not match")
            }
        }
    }
}