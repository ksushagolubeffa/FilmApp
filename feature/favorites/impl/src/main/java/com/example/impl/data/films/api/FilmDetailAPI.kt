package com.example.impl.data.films.api

import com.example.impl.data.films.response.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmDetailAPI {

    @GET("movies/getdetails")
    suspend fun getFilm(
        @Query("movie_id") movie_id: Int
    )
            : FilmResponse
}