package com.example.features.add_marker

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAddMarkerRouting() {
    routing {
        post("/markers/add") {
            val addMarkerController = AddMarkerController(call)
            addMarkerController.addMarker()
        }
    }
}