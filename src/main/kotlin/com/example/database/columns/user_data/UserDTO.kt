package com.example.database.columns.user_data

import com.example.database.column_types.Point


class UserDTO(
    val id: Long?,
    val login: String?,
    val password: String,
    val phoneNumber: String,
    val clearedPoints: List<Point>?
)