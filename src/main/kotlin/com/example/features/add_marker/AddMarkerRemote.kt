package com.example.features.add_marker

import com.example.database.column_types.Point
import kotlinx.serialization.Serializable

@Serializable
data class AddMarkerReceiveRemote(
    val phoneNumber: String,
    val markerLocation: Point,
    val description: String
)

@Serializable
data class AddMarkerResponseRemote(
    val markerId: Long
)

