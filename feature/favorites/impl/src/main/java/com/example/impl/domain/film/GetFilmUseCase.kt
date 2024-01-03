package com.example.impl.domain.film

import android.util.Log
import com.example.api.interfaces.film.FilmDetailRepository
import com.example.api.model.film.FilmModel

class GetFilmUseCase(
    private val repository: FilmDetailRepository,
) {

    suspend operator fun invoke(id: Int): FilmModel {
        Log.e("GetFilmUseCase", "try to get")
        return repository.getFilm(id)
    }
}