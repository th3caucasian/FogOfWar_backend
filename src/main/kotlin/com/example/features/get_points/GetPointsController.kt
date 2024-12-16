package com.example.features.get_points

import com.example.database.columns.user_data.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class GetPointsController(private val call: ApplicationCall) {

    suspend fun getPoints() {

        val getPointsReceiveRemote = call.receive<GetPointsReceiveRemote>()

        val points = UserData.getUserPoints(getPointsReceiveRemote.phoneNumber)

        val getPointsResponseRemote = GetPointsResponseRemote(points)

        if (points != null)
            call.respond(getPointsResponseRemote)
        else
            call.respond(HttpStatusCode.BadRequest)
    }
}