package com.example.database.data.repository

import com.example.database.data.entity.UserEntity

interface UserRepository {

    suspend fun getById(id: Int): UserEntity?

    suspend fun getByEmail(email: String): UserEntity?

    suspend fun getByEmailAndPassword(email: String, password: ByteArray): UserEntity?

    suspend fun deleteUser(id: Int)

    suspend fun createUser(email: String, username: String, password: ByteArray)

    suspend fun updateUser(
        id: Int,
        email: String,
        username: String,
        password: ByteArray,
        points: Int
    )
}