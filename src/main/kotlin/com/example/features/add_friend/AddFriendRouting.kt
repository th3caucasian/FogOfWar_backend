package com.example.features.add_friend

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAddFriendRouting() {
    routing {
        post("/friends/add") {
            val addFriendController = AddFriendController(call)
            addFriendController.addFriend()
        }
    }
}