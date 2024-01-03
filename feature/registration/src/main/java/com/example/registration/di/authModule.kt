package com.example.registration.di

import com.example.database.data.repository.UserRepository
import com.example.registration.domain.CreateUserUseCase
import com.example.registration.domain.UserByEmailAndPasswordUseCase
import com.example.registration.domain.UserByEmailUseCase
import com.example.registration.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { AuthViewModel(get(), get(), get()) }
    factory { provideCreateUserUseCase(get()) }
    factory { provideUserByEmailUseCase(get()) }
    factory { provideUserByEmailAndPasswordUseCase(get()) }
}

fun provideCreateUserUseCase(
    repository: UserRepository
): CreateUserUseCase = CreateUserUseCase(repository)

fun provideUserByEmailAndPasswordUseCase(
    repository: UserRepository
): UserByEmailAndPasswordUseCase = UserByEmailAndPasswordUseCase(repository)

fun provideUserByEmailUseCase(
    repository: UserRepository
): UserByEmailUseCase = UserByEmailUseCase(repository)