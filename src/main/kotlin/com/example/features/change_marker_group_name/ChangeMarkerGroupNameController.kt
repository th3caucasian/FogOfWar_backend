package com.example.features.change_marker_group_name

import com.example.database.columns.marker_group.MarkerGroup
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ChangeMarkerGroupNameController(private val call: ApplicationCall) {

    suspend fun changeMarkerGroupPrivacy() {

        try {
            val changeMarkerGroupNameReceiveRemote = call.receive<ChangeMarkerGroupNameReceiveRemote>()

            MarkerGroup.changeName(changeMarkerGroupNameReceiveRemote.markerGroupId, changeMarkerGroupNameReceiveRemote.name)
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}