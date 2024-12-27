package com.example.features.change_marker_group_privacy

import com.example.database.columns.marker_group.MarkerGroup
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ChangeMarkerGroupPrivacyController(private val call: ApplicationCall) {

    suspend fun changeMarkerGroupPrivacy() {

        try {
            val changeMarkerGroupPrivacyReceiveRemote = call.receive<ChangeMarkerGroupPrivacyReceiveRemote>()

            MarkerGroup.changePrivacy(changeMarkerGroupPrivacyReceiveRemote.markerGroupId, changeMarkerGroupPrivacyReceiveRemote.privacy)

            call.respond(HttpStatusCode.OK)
        }
        catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}