package com.example.features.get_points

import com.example.database.column_types.Point
import kotlinx.serialization.Serializable

@Serializable
data class GetPointsReceiveRemote(
    val phoneNumber: String,
)

@Serializable
data class GetPointsResponseRemote(
    val points: List<Point>?
)