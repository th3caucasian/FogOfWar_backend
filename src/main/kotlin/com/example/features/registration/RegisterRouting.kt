package com.example.features.registration

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.features.login.LoginReceiveRemote
import com.example.features.login.LoginResponseRemote
import com.example.utils.isValidNumber
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}

