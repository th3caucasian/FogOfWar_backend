package com.example.features.add_friend

import kotlinx.serialization.Serializable

@Serializable
data class AddFriendReceiveRemote(
    val userNumber: String,
    val userNumberToAdd: String
)

