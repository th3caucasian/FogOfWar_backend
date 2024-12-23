package com.example.features.change_marker_group_privacy

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureChangeMarkerGroupPrivacyRouting() {
    routing {
        post("/marker-group/change-privacy") {
            val changeMarkerGroupPrivacyController = ChangeMarkerGroupPrivacyController(call)
            changeMarkerGroupPrivacyController.changeMarkerGroupPrivacy()
        }
    }
}