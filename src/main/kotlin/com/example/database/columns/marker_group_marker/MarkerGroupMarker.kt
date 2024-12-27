package com.example.database.columns.marker_group_marker

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker_group.MarkerGroup
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object MarkerGroupMarker: Table("marker_group_marker") {
    val groupId = long("group_id").references(MarkerGroup.id)
    val markerId = long("marker_id").references(Marker.id)

    override val primaryKey = PrimaryKey(groupId, markerId, name = "PK_marker_group_marker")

    fun insert(markerGroupMarkerDTO: MarkerGroupMarkerDTO) {
        transaction {
            MarkerGroupMarker.insert {
                it[groupId] = markerGroupMarkerDTO.groupId
                it[markerId] = markerGroupMarkerDTO.markerId
            }
        }
    }

    fun delete(currentMarkerId: Long, currentGroupId: Long) {
        transaction {
            MarkerGroupMarker.deleteWhere { (MarkerGroupMarker.markerId eq currentMarkerId) and (MarkerGroupMarker.groupId eq currentGroupId) }
        }
    }

}