package com.example.features.get_markers

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetMarkerRouting() {
    routing {
        post("/markers/get") {
            val getMarkerController = GetMarkerController(call)
            getMarkerController.getMarkers()
        }
    }
}