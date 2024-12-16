package com.example.database.columns.marker_group

import com.example.database.columns.marker.Marker
import com.example.database.columns.user_data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object MarkerGroup: Table("marker_group") {
    val id = long("id").autoIncrement()
    private val markerId = long("marker_id").references(Marker.id)
    private val userId = long("user_id").references(UserData.id)
    private val name = varchar("name", 50).uniqueIndex("UI_marker_group_name")
    private val description = varchar("description", 200)
    private val privacy = bool("privacy").default(true)

    override val primaryKey = PrimaryKey(id, name = "PK_marker_group")

    fun insert(markerGroupDTO: MarkerGroupDTO) {
        transaction {
            MarkerGroup.insert {
                it[markerId] = markerGroupDTO.markerId
                it[userId] = markerGroupDTO.userId
                it[name] = markerGroupDTO.name
                it[description] = markerGroupDTO.description
                it[privacy] = markerGroupDTO.privacy
            }
        }
    }

}