package com.example.features.delete_marker_from_group

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDeleteMarkerFromGroupRouting() {
    routing {
        post("/marker-group/delete-marker") {
            val deleteMarkerFromGroupController = DeleteMarkerFromGroupController(call)
            deleteMarkerFromGroupController.deleteMarkerFromGroup()
        }
    }
}