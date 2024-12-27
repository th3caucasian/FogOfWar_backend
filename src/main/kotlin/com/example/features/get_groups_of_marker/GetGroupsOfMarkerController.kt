package com.example.features.get_groups_of_marker

import com.example.database.columns.marker_group.MarkerGroup
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GetGroupsOfMarkerController(private val call: ApplicationCall) {

    suspend fun getGroupsOfMarker() {

        val getGroupsOfMarkerReceiveRemote = call.receive<GetGroupsOfMarkerReceiveRemote>()


        try {
            val groupsOfMarker = MarkerGroup.getGroupsOfMarker(getGroupsOfMarkerReceiveRemote.markerId)

            call.respond(GetGroupsOfMarkerResponseRemote(groupsOfMarker))
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}