package com.example.database.column_types

import org.jetbrains.exposed.sql.ColumnType


class PointArrayColumnType: ColumnType<List<Point>>() {
    override fun sqlType(): String {
        return "Point[]"
    }

    // Функция рассчитана на получение данных типа Point[] из БД. Обязательное условие - две координаты в каждом элементе массива
    override fun valueFromDB(value: Any): List<Point> {
        val parsedQuery = value.toString().filterNot { it == '{' || it == '}' || it == '(' || it == ')' || it == '\"'}.split(",")
        val values = mutableListOf<Point>()
        for (i in parsedQuery.indices step 2) {
            values.add(Point(parsedQuery[i].toDouble(), parsedQuery[i+1].toDouble()))
        }
        return values
    }

    override fun notNullValueToDB(value: List<Point>): Any {
        var finalString = "ARRAY ["
        for (i in value.indices) {
            finalString += "Point(${value[i].latitude},${value[i].longitude}),"
        }
        finalString = finalString.dropLast(1)
        finalString += ']'
        return finalString
    }

    override fun valueToDB(value: List<Point>?): Any? {
        return value?.let { notNullValueToDB(it) }
    }
}