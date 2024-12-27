package com.example

import com.example.features.add_friend.configureAddFriendRouting
import com.example.features.add_marker.configureAddMarkerRouting
import com.example.features.add_marker_group.configureAddMarkerGroupRouting
import com.example.features.add_marker_to_group.configureAddMarkerToGroupRouting
import com.example.features.change_marker_group_name.configureChangeMarkerGroupNameRouting
import com.example.features.change_marker_group_privacy.configureChangeMarkerGroupPrivacyRouting
import com.example.features.delete_friend.configureDeleteFriendRouting
import com.example.features.delete_marker.configureDeleteMarkerRouting
import com.example.features.delete_marker_from_group.configureDeleteMarkerFromGroupRouting
import com.example.features.delete_marker_group.configureDeleteMarkerGroupRouting
import com.example.features.get_friends.configureGetFriendsRouting
import com.example.features.get_groups_of_marker.configureGetGroupsOfMarkerRouting
import com.example.features.get_marker_groups.configureGetMarkerGroupsRouting
import com.example.features.get_markers.configureGetMarkerRouting
import com.example.features.get_points.configureGetPointsRouting
import com.example.features.get_user.configureGetUserRouting
import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegisterRouting
import com.example.features.share_marker_group.configureShareMarkerGroupRouting
import com.example.features.update_points.configureUpdatePointsRouting
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import java.io.File

fun main() {
    val dbInfo = readDBInfo("C:\\Users\\user\\IdeaProjects\\database.txt")
    Database.connect(url = "jdbc:postgresql://localhost:2000/postgres", driver = "org.postgresql.Driver", user = dbInfo[0], password = dbInfo[1])
    embeddedServer(CIO, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureUpdatePointsRouting()
    configureGetPointsRouting()
    configureGetMarkerRouting()
    configureAddMarkerRouting()
    configureDeleteMarkerRouting()
    configureAddMarkerGroupRouting()
    configureShareMarkerGroupRouting()
    configureAddMarkerToGroupRouting()
    configureChangeMarkerGroupPrivacyRouting()
    configureAddFriendRouting()
    configureGetUserRouting()
    configureGetFriendsRouting()
    configureGetMarkerGroupsRouting()
    configureDeleteMarkerGroupRouting()
    configureDeleteFriendRouting()
    configureChangeMarkerGroupNameRouting()
    configureGetGroupsOfMarkerRouting()
    configureDeleteMarkerFromGroupRouting()

}

fun readDBInfo(fileName: String): List<String> {
    val databaseInfo = mutableListOf<String>()
    File(fileName).forEachLine { line ->
        databaseInfo.add(line)
    }
    return databaseInfo
}