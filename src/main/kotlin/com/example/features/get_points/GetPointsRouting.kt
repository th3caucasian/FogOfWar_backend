package com.example.features.get_points

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetPointsRouting() {
    routing {
        post("/points/get") {
            val getPointsController = GetPointsController(call)
            getPointsController.getPoints()
        }
    }
}