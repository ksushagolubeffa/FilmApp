package com.example.impl.data

import android.util.Log
import com.example.api.interfaces.FilmRepository
import com.example.api.model.FilmListModel
import com.example.impl.data.api.FilmAPI

class FilmRepositoryImpl(private val api: FilmAPI) : FilmRepository {

    override suspend fun getFilmsList(genre: Int): List<FilmListModel>{
        val res = api.getFilmsByGenre(genre).toFilmList()
        Log.e("repository after got", res.toString())
        return res
    }
}
