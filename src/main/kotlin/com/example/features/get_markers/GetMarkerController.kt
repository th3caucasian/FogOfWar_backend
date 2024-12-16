package com.example.features.get_markers

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker.MarkerDTO
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.marker_user_data.MarkerUserDataDTO
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GetMarkerController(private val call: ApplicationCall) {

    suspend fun getMarkers() {

        try {
            val getMarkersReceiveRemote = call.receive<GetMarkersReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(getMarkersReceiveRemote.phoneNumber).id!!
            val markersIds = MarkerUserData.getUserMarkersIds(currentUserId)
            val markersList = mutableListOf<MarkerDTO>()
            for (elem in markersIds) {
                markersList.add(Marker.getMarkerInfo(elem))
            }
            call.respond(GetMarkersResponseRemote(markersList))
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }



    }
}