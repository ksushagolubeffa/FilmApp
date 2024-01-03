package com.example.impl.data.films

import com.example.api.model.film.FilmModel
import com.example.impl.data.films.response.Company
import com.example.impl.data.films.response.Country
import com.example.impl.data.films.response.FilmResponse
import com.example.impl.data.films.response.Genre

fun FilmResponse.toFilmInfo(): FilmModel {
    return FilmModel(
        id = id,
        title = title,
        image = image,
        description = description,
        budget = budget,
        tagline = tagline,
        release_date = release_date,
        vote_average = vote_average,
        runtime = runtime,
        genres = getGenres(genres),
        production_companies = getCompanies(production_companies),
        production_countries = getCountries(production_countries)
    )
}

fun getGenres(genres: List<Genre>): List<String>{
    val ans = mutableListOf<String>()
    genres.forEach {
        ans.add(it.name)
    }
    return ans
}

fun getCountries(countries: List<Country>): List<String>{
    val ans = mutableListOf<String>()
    countries.forEach {
        ans.add(it.name)
    }
    return ans
}

fun getCompanies(companies: List<Company>): List<String>{
    val ans = mutableListOf<String>()
    companies.forEach {
        ans.add(it.name)
    }
    return ans
}