package com.example.features.get_markers

import com.example.database.column_types.Point
import com.example.database.columns.marker.MarkerDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetMarkersReceiveRemote(
    val phoneNumber: String
)

@Serializable
data class GetMarkersResponseRemote(
    val markers: List<MarkerDTO>
)
