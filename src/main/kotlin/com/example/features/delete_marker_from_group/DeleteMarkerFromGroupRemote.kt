package com.example.features.delete_marker_from_group

import kotlinx.serialization.Serializable

@Serializable
data class DeleteMarkerFromGroupReceiveRemote(
    val markerId: Long,
    val groupId: Long
)
