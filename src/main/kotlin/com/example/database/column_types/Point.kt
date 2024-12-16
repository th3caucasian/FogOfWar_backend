package com.example.database.column_types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Point (
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double
)