package com.example.impl.data.films.response

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("overview")
    val description: String,
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("production_companies")
    val production_companies: List<Company>,
    @SerializedName("production_countries")
    val production_countries: List<Country>,
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Company(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Country(
    @SerializedName("name")
    val name: String
)