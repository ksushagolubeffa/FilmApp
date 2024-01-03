package com.example.impl.domain.favorite

import com.example.api.interfaces.favorite.FavoriteRepository

class DeleteFavoriteUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(id: Int){
        repository.deleteFromFavorite(id)
    }
}