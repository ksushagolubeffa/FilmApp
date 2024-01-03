package com.example.impl.presentation.viewmodel

import androidx.compose.runtime.Immutable
import com.example.api.model.FilmListModel

@Immutable
data class FilmsScreenState(
    val error: Throwable? = null,
    val filmListInfo: List<FilmListModel>? = null
)