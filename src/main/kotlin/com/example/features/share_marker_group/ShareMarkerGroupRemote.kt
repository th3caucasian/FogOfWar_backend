package com.example.features.add_marker_group

import kotlinx.serialization.Serializable

@Serializable
data class AddMarkerGroupReceiveRemote(
    val phoneNumber: String,
    val name: String,
    val description: String,
    val privacy: Boolean
)

