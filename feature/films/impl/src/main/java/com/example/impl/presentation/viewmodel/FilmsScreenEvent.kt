package com.example.impl.presentation.viewmodel

sealed interface FilmsScreenEvent {
    data class OnLoadListInfo(val genre: Int): FilmsScreenEvent
}