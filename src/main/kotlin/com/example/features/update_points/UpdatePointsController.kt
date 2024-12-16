package com.example.features.update_points

import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UpdatePointsController(private val call: ApplicationCall) {

    suspend fun updatePoints() {

        val updatePointsReceiveRemote = call.receive<UpdatePointsReceiveRemote>()

        val success = UserData.addClearedPoints(updatePointsReceiveRemote.phoneNumber, updatePointsReceiveRemote.clearedPoints)

        if (success)
            call.respond(HttpStatusCode.OK)
        else
            call.respond(HttpStatusCode.BadRequest)
    }
}