package com.example.features.delete_marker

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDeleteMarkerRouting() {
    routing {
        post("/markers/delete") {
            val deleteMarkerController = DeleteMarkerController(call)
            deleteMarkerController.deleteMarker()
        }
    }
}