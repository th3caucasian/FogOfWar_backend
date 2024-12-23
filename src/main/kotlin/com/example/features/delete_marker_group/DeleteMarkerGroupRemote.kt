package com.example.features.delete_marker_group

import kotlinx.serialization.Serializable

@Serializable
data class DeleteMarkerGroupReceiveRemote(
    val phoneNumber: String,
    val markerGroupId: Long
)
