package com.example.favorite_database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Favorite::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}
