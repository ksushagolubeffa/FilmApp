package com.example.impl.data.api

import com.example.impl.data.response.FilmListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {

    @GET("discover/movie")
    suspend fun getFilmsByGenre(
        @Query("with_genres") with_genres: Int,
        @Query("pages") pages: Int = 1
    )
    : FilmListResponse
}
