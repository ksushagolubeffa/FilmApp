package com.example.impl.domain.favorite

import com.example.api.interfaces.favorite.FavoriteRepository
import com.example.api.model.favorite.FavoriteEntity

class GetAllFavoriteUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(): List<FavoriteEntity>?{
        return repository.getAll()
    }
}