package com.example.features.get_marker_groups

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetMarkerGroupsRouting() {
    routing {
        post("/marker-group/get") {
            val getMarkerGroupsController = GetMarkerGroupsController(call)
            getMarkerGroupsController.getMarkerGroups()
        }
    }
}