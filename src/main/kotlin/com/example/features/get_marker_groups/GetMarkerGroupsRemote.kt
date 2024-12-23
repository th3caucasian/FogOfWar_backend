package com.example.features.get_marker_groups

import com.example.database.columns.marker_group.MarkerGroupDTO
import kotlinx.serialization.Serializable

@Serializable
data class GetMarkerGroupsReceiveRemote(
    val phoneNumber: String,
)


@Serializable
data class GetMarkerGroupsResponseRemote(
    val markerGroups: List<MarkerGroupDTO>
)