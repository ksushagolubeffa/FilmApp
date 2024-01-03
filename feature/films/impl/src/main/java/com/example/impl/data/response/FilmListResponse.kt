package com.example.impl.data.response

import com.google.gson.annotations.SerializedName

data class FilmListResponse(
    @SerializedName("results")
    val results: List<ListResponse>,
)

data class ListResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val vote_average: Double,
)
