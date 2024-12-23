package com.example.features.add_marker_group

import com.example.database.columns.marker_group.MarkerGroup
import com.example.database.columns.marker_group.MarkerGroupDTO
import com.example.database.columns.marker_group_user_data.MarkerGroupUserData
import com.example.database.columns.marker_group_user_data.MarkerGroupUserDataDTO
import com.example.database.columns.user_data.UserData
import com.example.features.add_marker.AddMarkerResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AddMarkerGroupController(private val call: ApplicationCall) {

    suspend fun addMarkerGroup() {

        try {
            val addMarkerGroupReceiveRemote = call.receive<AddMarkerGroupReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(addMarkerGroupReceiveRemote.phoneNumber).id!!

            val currentGroupId = MarkerGroup.insert(
                MarkerGroupDTO(
                    id = null,
                    userId = currentUserId,
                    name = addMarkerGroupReceiveRemote.name,
                    description = addMarkerGroupReceiveRemote.description,
                    privacy = addMarkerGroupReceiveRemote.privacy
                )
            )

            MarkerGroupUserData.insert(
                MarkerGroupUserDataDTO(
                    groupId = currentGroupId,
                    userId = currentUserId
                )
            )

            call.respond(AddMarkerResponseRemote(currentUserId))

        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }



    }
}