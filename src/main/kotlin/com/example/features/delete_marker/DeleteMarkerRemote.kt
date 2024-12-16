package com.example.features.delete_marker

import kotlinx.serialization.Serializable

@Serializable
data class DeleteMarkerReceiveRemote(
    val phoneNumber: String,
    val markerId: Long
)
