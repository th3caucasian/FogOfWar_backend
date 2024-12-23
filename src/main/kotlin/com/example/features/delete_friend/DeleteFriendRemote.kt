package com.example.features.delete_friend

import kotlinx.serialization.Serializable

@Serializable
data class DeleteFriendReceiveRemote(
    val phoneNumber: String,
    val friendNumber: Long
)
