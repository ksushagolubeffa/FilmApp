package com.example.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class UserDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
