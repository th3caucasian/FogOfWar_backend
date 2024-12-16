package com.example.database.columns.marker_group

class MarkerGroupDTO(
    val id: Long?,
    val markerId: Long,
    val userId: Long,
    val name: String,
    val description: String,
    val privacy: Boolean
)