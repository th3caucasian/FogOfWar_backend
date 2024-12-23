package com.example.features.get_friends

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetFriendsRouting() {
    routing {
        post("/friends/get") {
            val getFriendsController = GetFriendsController(call)
            getFriendsController.getFriends()
        }
    }
}