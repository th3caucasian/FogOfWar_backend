package com.example.features.add_marker_to_group

import kotlinx.serialization.Serializable

@Serializable
data class AddMarkerToGroupReceiveRemote(
    val markerId: Long,
    val groupId: Long
)
