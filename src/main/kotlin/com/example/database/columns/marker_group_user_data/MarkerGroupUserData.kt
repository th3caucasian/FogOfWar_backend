package com.example.database.columns.marker_group_user_data

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker_group.MarkerGroup
import com.example.database.columns.user_data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object MarkerGroupUserData: Table("marker_group_user_data") {
    private val groupId = long("group_id").references(MarkerGroup.id)
    private val userId = long("user_id").references(UserData.id)

    override val primaryKey = PrimaryKey(groupId, userId, name = "PK_marker_group_user_data")

    fun insert(markerGroupUserDataDTO: MarkerGroupUserDataDTO) {
        transaction {
            MarkerGroupUserData.insert {
                it[groupId] = markerGroupUserDataDTO.groupId
                it[userId] = markerGroupUserDataDTO.userId
            }
        }
    }

}