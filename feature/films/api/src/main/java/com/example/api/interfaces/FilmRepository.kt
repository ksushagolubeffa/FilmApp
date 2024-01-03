package com.example.api.interfaces

import com.example.api.model.FilmListModel

interface FilmRepository {
//    suspend fun getFilm(id: Int): com.example.api.model.FilmModel
    suspend fun getFilmsList(genre: Int): List<FilmListModel>
}