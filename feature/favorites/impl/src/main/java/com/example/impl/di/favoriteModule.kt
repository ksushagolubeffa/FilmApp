package com.example.impl.di

import com.example.api.interfaces.favorite.FavoriteRepository
import com.example.favorite_database.data.FavoriteDao
import com.example.impl.data.favorite.FavoriteRepositoryImpl
import com.example.impl.domain.favorite.AddToFavoriteUseCase
import com.example.impl.domain.favorite.DeleteFavoriteUseCase
import com.example.impl.domain.favorite.GetAllFavoriteUseCase
import com.example.impl.domain.favorite.GetFavoriteByIdUseCase
import com.example.impl.presentation.favorite.viewmodel.FavViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module{
    viewModel{ FavViewModel(get(), get(), get(), get()) }

    single { provideFavoriteRepository(get()) }
    factory { provideAddToFavoriteUseCase(get()) }
    factory { provideDeleteFavoriteUseCase(get()) }
    factory { provideGetAllFavoriteUseCase(get()) }
    factory { provideGetFavoriteByIdUseCase(get()) }
}

private fun provideAddToFavoriteUseCase(
    repository: FavoriteRepository,
): AddToFavoriteUseCase =
    AddToFavoriteUseCase(repository)

private fun provideDeleteFavoriteUseCase(
    repository: FavoriteRepository,
): DeleteFavoriteUseCase =
    DeleteFavoriteUseCase(repository)

private fun provideGetAllFavoriteUseCase(
    repository: FavoriteRepository,
): GetAllFavoriteUseCase =
    GetAllFavoriteUseCase(repository)

private fun provideGetFavoriteByIdUseCase(
    repository: FavoriteRepository,
): GetFavoriteByIdUseCase =
    GetFavoriteByIdUseCase(repository)

private fun provideFavoriteRepository(
    favoriteDao: FavoriteDao
): FavoriteRepository =
    FavoriteRepositoryImpl(favoriteDao)