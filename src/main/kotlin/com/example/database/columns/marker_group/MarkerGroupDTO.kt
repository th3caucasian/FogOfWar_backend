package com.example.database.columns.marker_group

import kotlinx.serialization.Serializable

@Serializable
class MarkerGroupDTO(
    val id: Long?,
    val userId: Long,
    val name: String,
    val description: String,
    val privacy: Boolean
)