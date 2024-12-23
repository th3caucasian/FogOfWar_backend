package com.example.features.change_marker_group_name

import kotlinx.serialization.Serializable

@Serializable
data class ChangeMarkerGroupNameReceiveRemote(
    val markerGroupId: Long,
    val name: String
)


