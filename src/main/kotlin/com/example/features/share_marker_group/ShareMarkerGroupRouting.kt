package com.example.features.share_marker_group

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureShareMarkerGroupRouting() {
    routing {
        post("/marker-group/share") {
            val shareMarkerGroupController = ShareMarkerGroupController(call)
            shareMarkerGroupController.shareMarkerGroup()
        }
    }
}