package com.example.features.get_user

import kotlinx.serialization.Serializable

@Serializable
data class GetUserReceiveRemote(
    val phoneNumber: String,
)
