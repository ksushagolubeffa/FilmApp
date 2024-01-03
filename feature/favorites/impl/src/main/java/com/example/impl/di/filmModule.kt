package com.example.impl.di

import com.example.api.interfaces.film.FilmDetailRepository
import com.example.impl.data.films.FilmDetailRepositoryImpl
import com.example.impl.data.films.api.FilmDetailAPI
import com.example.impl.domain.film.GetFilmUseCase
import com.example.impl.presentation.film.viewmodel.FilmDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val filmModule = module{
    viewModel{ FilmDetailViewModel(get()) }
    factory { provideGetFilmUseCase(get()) }
    factory {
        get<Retrofit>().create(FilmDetailAPI::class.java)
    }
    single{ provideRepository(get()) }
}

private fun provideGetFilmUseCase(
    filmRepository: FilmDetailRepository,
): GetFilmUseCase =
    GetFilmUseCase(filmRepository)

private fun provideRepository(api: FilmDetailAPI): FilmDetailRepository =
    FilmDetailRepositoryImpl(api)