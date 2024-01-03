package com.example.impl.domain.favorite

import com.example.api.interfaces.favorite.FavoriteRepository
import com.example.api.model.favorite.FavoriteEntity

class GetFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(id: Int): FavoriteEntity?{
        return repository.getById(id)
    }
}