package com.example.impl.di

import com.example.impl.domain.GetFilmListUseCase
import com.example.impl.presentation.viewmodel.FilmsViewModel
import com.example.api.interfaces.FilmRepository
import com.example.impl.data.FilmRepositoryImpl
import com.example.impl.data.api.FilmAPI
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val filmsModule = module {
    viewModel { FilmsViewModel(get()) }
    factory { provideGetFilmListUseCase(get()) }
    single{ provideRepository(get()) }
    factory {
        get<Retrofit>().create(FilmAPI::class.java)
    }
}

private fun provideGetFilmListUseCase(
    filmRepository: FilmRepository,
): GetFilmListUseCase = GetFilmListUseCase(filmRepository)

private fun provideRepository(api: FilmAPI): FilmRepository =
    FilmRepositoryImpl(api)

