package com.example.database.column_types

import org.jetbrains.exposed.sql.ColumnType


class PointColumnType: ColumnType<Point>() {
    override fun sqlType(): String {
        return "Point"
    }

    // Функция рассчитана на получение данных типа Point[] из БД. Обязательное условие - две координаты в каждом элементе массива
    override fun valueFromDB(value: Any): Point {
        val parsedQuery = value.toString().filterNot { it == '(' || it == ')' || it == '\"'}.split(",")
        val point = Point(parsedQuery[0].toDouble(), parsedQuery[1].toDouble())
        return point
    }

    override fun notNullValueToDB(value: Point): Any {
        val finalString = "Point(${value.latitude},${value.longitude})"
        return finalString
    }

    override fun valueToDB(value: Point?): Any? {
        return value?.let { notNullValueToDB(it) }
    }
}