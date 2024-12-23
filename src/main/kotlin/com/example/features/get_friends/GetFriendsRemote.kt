package com.example.features.get_friends

import kotlinx.serialization.Serializable

@Serializable
data class GetFriendsReceiveRemote(
    val phoneNumber: String,
)


@Serializable
data class GetFriendsResponseRemote(
    val friendsNumbers: List<String>
)