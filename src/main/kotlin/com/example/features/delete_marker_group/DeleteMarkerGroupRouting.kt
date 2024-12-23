package com.example.features.delete_marker_group

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDeleteMarkerGroupRouting() {
    routing {
        post("/marker-group/delete") {
            val deleteMarkerGroupController = DeleteMarkerGroupController(call)
            deleteMarkerGroupController.deleteMarkerGroup()
        }
    }
}