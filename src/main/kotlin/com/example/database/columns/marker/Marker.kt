package com.example.database.columns.marker

import com.example.database.column_types.PointColumnType
import com.example.database.columns.marker_user_data.MarkerUserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Marker: Table("marker") {
    val id = long("id").autoIncrement()
    private val location = registerColumn("location", PointColumnType())
    private val description = varchar("description", 200).nullable()

    override val primaryKey = PrimaryKey(id, name = "PK_marker")


    fun insert(markerDTO: MarkerDTO): Long {
        return transaction {
            Marker.insert {
                it[location] = LiteralOp(PointColumnType(), markerDTO.location)
                it[description] = markerDTO.description
            } get Marker.id
        }
    }

    fun delete(currentMarkerId: Long) {
        if (!MarkerUserData.containsMarker(currentMarkerId)) {
            transaction {
                Marker.deleteWhere { id eq currentMarkerId }
            }
        }
    }

    fun getMarkerInfo(currentMarkerId: Long): MarkerDTO {
        return transaction {
            val markerDTO = Marker.selectAll().where(Marker.id eq currentMarkerId).single()
            MarkerDTO(
                id = markerDTO[Marker.id],
                location = markerDTO[location],
                description = markerDTO[description]
            )
        }
    }

}