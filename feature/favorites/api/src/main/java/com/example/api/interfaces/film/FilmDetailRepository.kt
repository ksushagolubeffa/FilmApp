package com.example.api.interfaces.film

import com.example.api.model.film.FilmModel

interface FilmDetailRepository {
    suspend fun getFilm(id: Int): FilmModel
}