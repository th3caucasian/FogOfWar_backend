package com.example.features.change_marker_group_privacy

import kotlinx.serialization.Serializable

@Serializable
data class ChangeMarkerGroupPrivacyReceiveRemote(
    val markerGroupId: Long,
    val privacy: Boolean
)


