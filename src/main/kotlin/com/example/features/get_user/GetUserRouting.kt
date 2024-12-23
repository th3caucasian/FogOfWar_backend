package com.example.features.get_user

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetUserRouting() {
    routing {
        post("/users/get") {
            val getUserController = GetUserController(call)
            getUserController.getUser()
        }
    }
}