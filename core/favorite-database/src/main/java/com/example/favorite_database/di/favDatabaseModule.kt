package com.example.favorite_database.di

import android.content.Context
import androidx.room.Room
import com.example.favorite_database.data.FavoriteDao
import com.example.favorite_database.data.FavoriteDatabase
import org.koin.dsl.module

val favoriteDatabaseModule = module {
    single { provideAppDatabase(get()) }
    factory { provideFavoriteDao(get()) }
}

fun provideAppDatabase(context: Context): FavoriteDatabase {
    return Room.databaseBuilder(context, FavoriteDatabase::class.java, "favoritr.db")
        .allowMainThreadQueries()
        .build()
}

fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
    return favoriteDatabase.getFavoriteDao()
}
