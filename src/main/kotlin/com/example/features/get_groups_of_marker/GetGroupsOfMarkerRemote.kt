package com.example.features.get_groups_of_marker

import kotlinx.serialization.Serializable

@Serializable
data class GetGroupsOfMarkerReceiveRemote(
    val markerId: Long
)


@Serializable
data class GetGroupsOfMarkerResponseRemote(
    val markerGroups: List<String>
)