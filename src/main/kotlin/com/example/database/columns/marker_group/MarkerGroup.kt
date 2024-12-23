package com.example.database.columns.marker_group

import com.example.database.columns.marker.Marker
import com.example.database.columns.marker_group_user_data.MarkerGroupUserData
import com.example.database.columns.marker_user_data.MarkerUserData
import com.example.database.columns.user_data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object MarkerGroup: Table("marker_group") {
    val id = long("id").autoIncrement()
    private val userId = long("user_id").references(UserData.id)
    private val name = varchar("name", 50).uniqueIndex("UI_marker_group_name")
    private val description = varchar("description", 200)
    private val privacy = bool("privacy").default(true)

    override val primaryKey = PrimaryKey(id, name = "PK_marker_group")

    fun insert(markerGroupDTO: MarkerGroupDTO): Long {
        return transaction {
            MarkerGroup.insert {
                it[userId] = markerGroupDTO.userId
                it[name] = markerGroupDTO.name
                it[description] = markerGroupDTO.description
                it[privacy] = markerGroupDTO.privacy
            } get MarkerGroup.id
        }
    }

    fun delete(currentMarkerGroupId: Long, currentUserId: Long) {
        transaction {
            val groupOwnerId = MarkerGroup.selectAll().where {MarkerGroup.id eq currentMarkerGroupId}.single()[userId]
            if (groupOwnerId == currentUserId) {
                MarkerGroupUserData.deleteWhere { MarkerGroupUserData.groupId eq currentMarkerGroupId }
            }
            else {
                MarkerGroupUserData.deleteWhere { (MarkerGroupUserData.groupId eq currentMarkerGroupId) and (MarkerGroupUserData.userId eq currentUserId)}
            }
        }
    }


    fun getMarkerGroups(currentUserId: Long): List<MarkerGroupDTO> {
        return transaction {
            val markerGroups = mutableListOf<MarkerGroupDTO>()
            MarkerGroup.selectAll().where {MarkerGroup.userId eq currentUserId }.forEach {
                markerGroups += MarkerGroupDTO (
                    id = it[MarkerGroup.id],
                    userId = it[userId],
                    name = it[name],
                    description = it[description],
                    privacy = it[privacy]
                )
            }
            markerGroups
        }
    }

    fun changePrivacy(markerGroupId: Long, privacy: Boolean) {
        transaction {
            MarkerGroup.update({ MarkerGroup.id eq markerGroupId }) { it[MarkerGroup.privacy] = !privacy }
        }
    }

    fun changeName(markerGroupId: Long, name: String) {
        transaction {
            MarkerGroup.update({ MarkerGroup.id eq markerGroupId }) { it[MarkerGroup.name] = name }
        }
    }

}