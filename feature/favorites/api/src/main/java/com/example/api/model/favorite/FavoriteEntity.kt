package com.example.api.model.favorite

data class FavoriteEntity(
    val id: Int,
    val title: String,
    val release_date: String,
    val image: String,
    val runtime: Int,
    val description: String,
    val budget: Long,
    val genres: List<String>,
    val tagline: String,
    val vote_average: Double,
    val production_companies: List<String>,
    val production_countries: List<String>,
)