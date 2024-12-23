package com.example.features.add_marker_to_group

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAddMarkerToGroupRouting() {
    routing {
        post("/marker-group/add-marker") {
            val addMarkerToGroupController = AddMarkerToGroupController(call)
            addMarkerToGroupController.addMarkerToGroup()
        }
    }
}