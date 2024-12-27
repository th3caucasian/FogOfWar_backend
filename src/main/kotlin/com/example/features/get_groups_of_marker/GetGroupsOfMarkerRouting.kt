package com.example.features.get_groups_of_marker

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetGroupsOfMarkerRouting() {
    routing {
        post("/marker/marker-group") {
            val getGroupsOfMarkerController = GetGroupsOfMarkerController(call)
            getGroupsOfMarkerController.getGroupsOfMarker()
        }
    }
}