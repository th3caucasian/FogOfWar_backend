package com.example.features.share_marker_group

import kotlinx.serialization.Serializable

@Serializable
data class ShareMarkerGroupReceiveRemote(
    val markerGroupId: Long,
    val phoneNumber: String
)

