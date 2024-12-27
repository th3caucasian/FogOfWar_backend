package com.example.features.delete_marker_from_group

import com.example.database.columns.marker_group_marker.MarkerGroupMarker
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DeleteMarkerFromGroupController(private val call: ApplicationCall) {

    suspend fun deleteMarkerFromGroup() {

        try {
            val deleteMarkerFromGroupReceiveRemote = call.receive<DeleteMarkerFromGroupReceiveRemote>()

            MarkerGroupMarker.delete(deleteMarkerFromGroupReceiveRemote.markerId, deleteMarkerFromGroupReceiveRemote.groupId)
            call.respond(HttpStatusCode.OK)
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}