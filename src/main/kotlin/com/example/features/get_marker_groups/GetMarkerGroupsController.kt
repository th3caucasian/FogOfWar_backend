package com.example.features.get_marker_groups

import com.example.database.columns.marker_group.MarkerGroup
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GetMarkerGroupsController(private val call: ApplicationCall) {

    suspend fun getMarkerGroups() {

        val getMarkerGroupsReceiveRemote = call.receive<GetMarkerGroupsReceiveRemote>()


        try {
            val userId = UserData.fetchUserByNumber(getMarkerGroupsReceiveRemote.phoneNumber).id!!
            val markerGroups = MarkerGroup.getMarkerGroups(userId)

            call.respond(GetMarkerGroupsResponseRemote(markerGroups))
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}