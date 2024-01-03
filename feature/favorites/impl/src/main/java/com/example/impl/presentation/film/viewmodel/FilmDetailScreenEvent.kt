package com.example.impl.presentation.film.viewmodel

sealed interface FilmDetailScreenEvent {
    data class OnLoadDetailInfo(val id: Int): FilmDetailScreenEvent
}