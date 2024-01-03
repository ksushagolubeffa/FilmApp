package com.example.favorite_database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "release_date") var release_date: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "runtime") var runtime: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "budget") var budget: Long,
    @ColumnInfo(name = "genres") var genres: List<String>,
    @ColumnInfo(name = "tagline") var tagline: String,
    @ColumnInfo(name = "vote_average") var vote_average: Double,
    @ColumnInfo(name = "production_companies") var production_companies: List<String>,
    @ColumnInfo(name = "production_countries") var production_countries: List<String>,
        )
