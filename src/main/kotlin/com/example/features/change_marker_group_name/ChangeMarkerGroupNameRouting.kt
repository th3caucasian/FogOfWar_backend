package com.example.features.change_marker_group_name

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureChangeMarkerGroupNameRouting() {
    routing {
        post("/marker-group/change-name") {
            val changeMarkerGroupNameController = ChangeMarkerGroupNameController(call)
            changeMarkerGroupNameController.changeMarkerGroupName()
        }
    }
}