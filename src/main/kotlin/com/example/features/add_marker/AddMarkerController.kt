package com.example.features.add_marker

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker.MarkerDTO
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.marker_user_data.MarkerUserDataDTO
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AddMarkerController(private val call: ApplicationCall) {

    suspend fun addMarker() {

        try {
            val saveMarkerReceiveRemote = call.receive<AddMarkerReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(saveMarkerReceiveRemote.phoneNumber).id!!

            val markerId = Marker.insert(MarkerDTO(
                id = null,
                location = saveMarkerReceiveRemote.markerLocation,
                description = saveMarkerReceiveRemote.description
            ))

            MarkerUserData.insert(
                MarkerUserDataDTO(
                    markerId = markerId,
                    userId = currentUserId
                )
            )

            call.respond(AddMarkerResponseRemote(markerId))
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }



    }
}