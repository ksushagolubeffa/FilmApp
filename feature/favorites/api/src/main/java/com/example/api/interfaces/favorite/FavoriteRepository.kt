package com.example.api.interfaces.favorite

import com.example.api.model.favorite.FavoriteEntity

interface FavoriteRepository {

    suspend fun getById(id: Int): FavoriteEntity?

    suspend fun getAll(): List<FavoriteEntity>?

    suspend fun addToFavorite(
        title: String,
        release_date: String,
        image: String,
        runtime: Int,
        description: String,
        budget: Long,
        genres: List<String>,
        tagline: String,
        vote_average: Double,
        production_companies: List<String>,
        production_countries: List<String>,
    )

    suspend fun deleteFromFavorite(id: Int)
}