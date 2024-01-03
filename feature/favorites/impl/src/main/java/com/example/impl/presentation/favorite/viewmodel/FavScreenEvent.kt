package com.example.impl.presentation.favorite.viewmodel

interface FavScreenEvent {
    data class OnAddToFavorite(
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
    ) : FavScreenEvent

    data class OnFindById(val id: Int): FavScreenEvent

    data class OnDeleteFavorite(val id: Int): FavScreenEvent

    data class OnGetAllFavorite(val count: Int): FavScreenEvent
}