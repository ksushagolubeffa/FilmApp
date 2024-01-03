package com.example.impl.presentation.favorite.viewmodel

import androidx.compose.runtime.Immutable
import com.example.api.model.favorite.FavoriteEntity

@Immutable
data class FavScreenState(
    val error: Throwable? = null,
    val filmInfo: FavoriteEntity? = null,
    val filmList: List<FavoriteEntity>?=null
)