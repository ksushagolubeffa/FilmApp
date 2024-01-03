package com.example.impl.domain.favorite

import com.example.api.interfaces.favorite.FavoriteRepository

class AddToFavoriteUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(
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
    ) {
        repository.addToFavorite(
            title,
            release_date,
            image,
            runtime,
            description,
            budget,
            genres,
            tagline,
            vote_average,
            production_companies,
            production_countries
        )
    }
}
