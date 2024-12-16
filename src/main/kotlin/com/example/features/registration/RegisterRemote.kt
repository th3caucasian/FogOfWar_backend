package com.example.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val phoneNumber: String,
    val password: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)