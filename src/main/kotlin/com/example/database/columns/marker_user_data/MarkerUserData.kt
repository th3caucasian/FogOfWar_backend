package com.example.database.columns.marker_user_data

import com.example.database.columns.marker.Marker
import com.example.database.columns.user_data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object MarkerUserData: Table("marker_user_data") {
    private val markerId = long("marker_id").references(Marker.id)
    private val userId = long("user_id").references(UserData.id)

    override val primaryKey = PrimaryKey(markerId, userId, name = "PK_marker_user_data")

    fun insert(markerUserDataDTO: MarkerUserDataDTO) {
        transaction {
            MarkerUserData.insert {
                it[markerId] = markerUserDataDTO.markerId
                it[userId] = markerUserDataDTO.userId
            }
        }
    }

    fun delete(currentMarkerId: Long, currentUserId: Long) {
        transaction {
            MarkerUserData.deleteWhere { (markerId eq currentMarkerId) and (userId eq currentUserId) }
        }
    }

    fun getUserMarkersIds(currentUserId: Long): List<Long> {
        return transaction {
            val markersIdsList = mutableListOf<Long>()
            MarkerUserData.select(markerId).where(userId eq currentUserId).forEach{markersIdsList.add(it[markerId])}
            markersIdsList
        }
    }

    fun containsMarker(currentMarkerId: Long): Boolean {
        return transaction {
            val userIds = MarkerUserData.selectAll().where(markerId eq currentMarkerId).singleOrNull()
            userIds != null
        }
    }

}