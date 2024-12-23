package com.example.features.add_marker_to_group


import com.example.database.columns.marker_group_marker.MarkerGroupMarker
import com.example.database.columns.marker_group_marker.MarkerGroupMarkerDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AddMarkerToGroupController(private val call: ApplicationCall) {

    suspend fun addMarkerToGroup() {

        try {
            val addMarkerToGroupReceiveRemote = call.receive<AddMarkerToGroupReceiveRemote>()

            MarkerGroupMarker.insert(
                MarkerGroupMarkerDTO(
                    groupId = addMarkerToGroupReceiveRemote.groupId,
                    markerId = addMarkerToGroupReceiveRemote.markerId
                )
            )

        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}