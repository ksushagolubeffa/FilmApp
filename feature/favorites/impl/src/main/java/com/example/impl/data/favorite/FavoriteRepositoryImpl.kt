package com.example.impl.data.favorite

import com.example.api.interfaces.favorite.FavoriteRepository
import com.example.api.model.favorite.FavoriteEntity
import com.example.favorite_database.data.Favorite
import com.example.favorite_database.data.FavoriteDao

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) :
    FavoriteRepository {

    override suspend fun getById(id: Int): FavoriteEntity? {
        return if (favoriteDao.getById(id) == null) {
            null
        } else {
            getFavorite(favoriteDao.getById(id)!!)
        }
    }

    override suspend fun getAll(): List<FavoriteEntity>? {
        val resp = favoriteDao.getAll()
        val ans = mutableListOf<FavoriteEntity>()
        if (resp == null) {
            return null
        }
        resp.forEach {
            ans.add(getFavorite(it))
        }
        return ans.toList()
    }

    override suspend fun addToFavorite(
        title: String,
        release_date: String,
        image: String,
        runtime: Int,
        description: String,
        budget: Long,
        genres: List<String>,
        tagline: String,
        vote_average: Double,
        production_companies: List<String>,
        production_countries: List<String>,
    ) {
        val favorite = Favorite(
            0,
            title,
            release_date,
            image,
            runtime,
            description,
            budget,
            genres,
            tagline,
            vote_average,
            production_companies,
            production_countries
        )
        favoriteDao.add(favorite)
    }

    override suspend fun deleteFromFavorite(id: Int) {
        favoriteDao.delete(id)
    }

    private fun getFavorite(model: Favorite): FavoriteEntity {

        return FavoriteEntity(
            model.id,
            model.title,
            model.release_date,
            model.image,
            model.runtime,
            model.description,
            model.budget,
            model.genres,
            model.tagline,
            model.vote_average,
            model.production_companies,
            model.production_countries
        )

    }
}
