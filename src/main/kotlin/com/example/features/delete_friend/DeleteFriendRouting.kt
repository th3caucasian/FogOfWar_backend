package com.example.features.delete_friend

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDeleteFriendRouting() {
    routing {
        post("/friends/delete") {
            val deleteFriendController = DeleteFriendController(call)
            deleteFriendController.deleteFriend()
        }
    }
}