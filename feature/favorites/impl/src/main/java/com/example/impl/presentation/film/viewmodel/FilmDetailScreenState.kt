package com.example.impl.presentation.film.viewmodel

import androidx.compose.runtime.Immutable
import com.example.api.model.film.FilmModel

@Immutable
data class FilmDetailScreenState(
    val error: Throwable? = null,
    val filmInfo: FilmModel? = null,
)