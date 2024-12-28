package com.example.features.registration

import com.example.database.columns.user_token.UserToken
import com.example.database.columns.user_token.TokenDTO
import com.example.database.columns.user_data.UserDTO
import com.example.database.columns.user_data.UserData
import com.example.utils.isValidNumber
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {

        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.phoneNumber.isValidNumber())
            call.respond(HttpStatusCode.BadRequest, "Invalid phone number")


        try {
            val userDTO = UserData.fetchUserByNumber(registerReceiveRemote.phoneNumber)
            call.respond(HttpStatusCode.Conflict, "User already exists")

        }
        catch (e: Exception) {
            UserData.insert(
                UserDTO(
                    id = null,
                    login = registerReceiveRemote.login,
                    password = registerReceiveRemote.password,
                    phoneNumber = registerReceiveRemote.phoneNumber,
                    clearedPoints = null
                )
            )

            val token = UUID.randomUUID().toString()
            UserToken.insert(
                TokenDTO(
                    id = null,
                    phoneNumber = registerReceiveRemote.phoneNumber,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token=token))
        }

    }
}