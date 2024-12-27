package com.example.features.delete_friend

import kotlinx.serialization.Serializable

@Serializable
data class DeleteFriendReceiveRemote(
    val userNumber: String,
    val friendNumber: String
)
