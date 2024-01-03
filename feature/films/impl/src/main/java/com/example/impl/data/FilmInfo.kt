package com.example.impl.data

import com.example.api.model.FilmListModel
import com.example.impl.data.response.FilmListResponse
import com.example.impl.data.response.ListResponse

fun ListResponse.toFilmInfo(): FilmListModel {
    return FilmListModel(
        id = id,
        title = title,
        overview = overview,
        vote_average = vote_average,
        release_date = release_date,
        poster_path = poster_path
    )
}

fun FilmListResponse.toFilmList(): List<FilmListModel> {
    val detailModels = mutableListOf<FilmListModel>()
    this.results.forEach { filmResponse ->
        detailModels.add(filmResponse.toFilmInfo())
    }
    return detailModels
}
