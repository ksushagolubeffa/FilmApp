package com.example.favorite_database.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split(",")?.map { it }
    }
}
