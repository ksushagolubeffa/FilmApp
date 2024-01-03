package com.example.impl.presentation.film.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impl.domain.film.GetFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val getFilmUseCase: GetFilmUseCase
): ViewModel() {

    private val _state = MutableStateFlow(FilmDetailScreenState())
    val state: StateFlow<FilmDetailScreenState> = _state.asStateFlow()

    private fun getFilmById(id: Int) {
        viewModelScope.launch {
            try {
                getFilmUseCase(id).also { filmInfo ->
                    _state.emit(
                        _state.value.copy(
                            filmInfo = filmInfo
                        )
                    )
                }

            } catch (error: Throwable) {
                _state.emit(
                    _state.value.copy(
                        error = error
                    )
                )

            }
        }
    }


    fun reducer(event: FilmDetailScreenEvent) {
        when (event) {
            is FilmDetailScreenEvent.OnLoadDetailInfo -> getFilmById(event.id)
        }
    }

}