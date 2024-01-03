package com.example.database.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: ByteArray,
    @ColumnInfo(name = "points") var points: Int,
)

