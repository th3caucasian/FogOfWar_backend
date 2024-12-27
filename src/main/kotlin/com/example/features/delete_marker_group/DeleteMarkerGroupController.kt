package com.example.features.delete_marker_group

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker_group.MarkerGroup
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DeleteMarkerGroupController(private val call: ApplicationCall) {

    suspend fun deleteMarkerGroup() {

        try {
            val deleteMarkerGroupReceiveRemote = call.receive<DeleteMarkerGroupReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(deleteMarkerGroupReceiveRemote.phoneNumber).id!!

            MarkerGroup.delete(deleteMarkerGroupReceiveRemote.markerGroupId, currentUserId)
            call.respond(HttpStatusCode.OK)
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}