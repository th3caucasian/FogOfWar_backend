package com.example.features.share_marker_group


import com.example.database.columns.marker_group_user_data.MarkerGroupUserData
import com.example.database.columns.marker_group_user_data.MarkerGroupUserDataDTO
import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ShareMarkerGroupController(private val call: ApplicationCall) {

    suspend fun shareMarkerGroup() {

        try {
            val shareMarkerGroupReceiveRemote = call.receive<ShareMarkerGroupReceiveRemote>()

            val currentUserId = UserData.fetchUserByNumber(shareMarkerGroupReceiveRemote.phoneNumber).id!!

            MarkerGroupUserData.insert(
                MarkerGroupUserDataDTO(
                    groupId = shareMarkerGroupReceiveRemote.markerGroupId,
                    userId = currentUserId
                )
            )
            call.respond(HttpStatusCode.OK)
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }



    }
}