package com.example.features.delete_marker

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker.MarkerDTO
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.marker_user_data.MarkerUserDataDTO
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DeleteMarkerController(private val call: ApplicationCall) {

    suspend fun deleteMarker() {

        try {
            val deleteMarkerReceiveRemote = call.receive<DeleteMarkerReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(deleteMarkerReceiveRemote.phoneNumber).id!!

            MarkerUserData.delete(deleteMarkerReceiveRemote.markerId, currentUserId)
            Marker.delete(deleteMarkerReceiveRemote.markerId)

            call.respond(HttpStatusCode.OK)

        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}