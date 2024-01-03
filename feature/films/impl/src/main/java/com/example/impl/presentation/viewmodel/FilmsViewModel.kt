package com.example.impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impl.domain.GetFilmListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val getFilmListUseCase: GetFilmListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FilmsScreenState())
    val state: StateFlow<FilmsScreenState> = _state.asStateFlow()

    private fun getFilmsList(genre: Int) {
        viewModelScope.launch {
            try {
                getFilmListUseCase(genre).also { filmListInfo ->
                    _state.emit(
                        _state.value.copy(
                            filmListInfo = filmListInfo
                        )
                    )
                    Log.e("viewmodel", filmListInfo.toString())
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

    fun reducer(event: FilmsScreenEvent) {
        when (event) {
            is FilmsScreenEvent.OnLoadListInfo -> getFilmsList(event.genre)
        }
    }

}