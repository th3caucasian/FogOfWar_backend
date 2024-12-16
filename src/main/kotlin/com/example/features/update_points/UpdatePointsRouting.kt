package com.example.features.update_points

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureUpdatePointsRouting() {
    routing {
        post("/points/update") {
            val updatePointsController = UpdatePointsController(call)
            updatePointsController.updatePoints()
        }
    }
}