package com.example.impl.presentation.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impl.domain.favorite.AddToFavoriteUseCase
import com.example.impl.domain.favorite.DeleteFavoriteUseCase
import com.example.impl.domain.favorite.GetAllFavoriteUseCase
import com.example.impl.domain.favorite.GetFavoriteByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavViewModel(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavScreenState())
    val state: StateFlow<FavScreenState> = _state.asStateFlow()

    private fun addToFavorite(
        title: String,
        release_date: String,
        image: String,
        runtime: Int,
        description: String,
        budget: Long,
        genres: List<String>,
        tagline: String,
        vote_average: Double,
        production_companies: List<String>,
        production_countries: List<String>,
    ) {
        viewModelScope.launch {
            try {
                addToFavoriteUseCase(
                    title,
                    release_date,
                    image,
                    runtime,
                    description,
                    budget,
                    genres,
                    tagline,
                    vote_average,
                    production_companies,
                    production_countries
                )
            } catch (error: Throwable) {
                _state.emit(
                    _state.value.copy(
                        error = error
                    )
                )

            }
        }
    }

    private fun getFavoriteById(id: Int) {
        viewModelScope.launch {
            try {
                getFavoriteByIdUseCase(id).also { filmInfo ->
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

    private fun getAllFavorite() {
        viewModelScope.launch {
            try {
                getAllFavoriteUseCase().also { filmList ->
                    _state.emit(
                        _state.value.copy(
                            filmList = filmList
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

    private fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            try {
                deleteFavoriteUseCase(id)
            } catch (error: Throwable) {
                _state.emit(
                    _state.value.copy(
                        error = error
                    )
                )
            }
        }
    }

    fun reducer(event: FavScreenEvent) {
        when (event) {
            is FavScreenEvent.OnAddToFavorite -> addToFavorite(
                event.title,
                event.release_date,
                event.image,
                event.runtime,
                event.description,
                event.budget,
                event.genres,
                event.tagline,
                event.vote_average,
                event.production_companies,
                event.production_countries
            )

            is FavScreenEvent.OnFindById -> getFavoriteById(event.id)
            is FavScreenEvent.OnGetAllFavorite -> getAllFavorite()
            is FavScreenEvent.OnDeleteFavorite -> deleteFavorite(event.id)
        }
    }
}