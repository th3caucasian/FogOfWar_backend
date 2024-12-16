package com.example.database.columns.marker

import com.example.database.column_types.Point
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MarkerDTO(
    @SerialName("id")
    val id: Long?,
    @SerialName("location")
    val location: Point,
    @SerialName("description")
    val description: String?,
)