package com.example.database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.database.data.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun add(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * from user WHERE id = :id")
    suspend fun getById(id: Int): User?

    @Query("SELECT * from user WHERE email = :email")
    suspend fun getByEmail(email: String): User?

    @Query("SELECT * from user WHERE email = :email and password = :password")
    suspend fun getUser(email: String, password: ByteArray): User?
}