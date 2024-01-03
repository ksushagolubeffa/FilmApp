package com.example.impl.domain

import android.util.Log
import com.example.api.model.FilmListModel
import com.example.api.interfaces.FilmRepository

class GetFilmListUseCase(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(genre: Int): List<FilmListModel> {
        Log.e("GetFilmListUseCase", "try to get")
        val resp = repository.getFilmsList(genre)
        Log.e("After got usecase", resp.toString())
        return resp
    }
}