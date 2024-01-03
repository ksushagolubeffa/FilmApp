package com.example.database.data.entity

data class UserEntity(
    val id: Int,
    val email: String,
    val username: String,
    val password: ByteArray,
    val points: Int = 0
)
