package com.example.impl.data.films

import com.example.api.interfaces.film.FilmDetailRepository
import com.example.api.model.film.FilmModel
import com.example.impl.data.films.api.FilmDetailAPI

class FilmDetailRepositoryImpl(private val api: FilmDetailAPI): FilmDetailRepository {
    override suspend fun getFilm(id: Int): FilmModel {
        return api.getFilm(id).toFilmInfo()
    }
}