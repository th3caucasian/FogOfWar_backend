package com.example.features.add_marker_group

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAddMarkerGroupRouting() {
    routing {
        post("/marker-group/add") {
            val addMarkerGroupController = AddMarkerGroupController(call)
            addMarkerGroupController.addMarkerGroup()
        }
    }
}