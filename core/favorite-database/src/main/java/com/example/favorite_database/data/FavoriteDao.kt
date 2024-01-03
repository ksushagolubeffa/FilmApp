package com.example.favorite_database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun add(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    suspend fun getAll(): List<Favorite>?

    @Query("SELECT * FROM favorite WHERE id = :id")
    suspend fun getById(id: Int): Favorite?

    @Query("DELETE from favorite WHERE id = :id")
    suspend fun delete(id: Int)
}
