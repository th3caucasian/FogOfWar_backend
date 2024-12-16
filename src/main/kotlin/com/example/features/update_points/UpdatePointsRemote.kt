package com.example.features.update_points

import com.example.database.column_types.Point
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePointsReceiveRemote(
    val phoneNumber: String,
    val clearedPoints: List<Point>
)
