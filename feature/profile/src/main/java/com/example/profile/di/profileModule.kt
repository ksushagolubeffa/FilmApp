package com.example.profile.di

import com.example.database.data.repository.UserRepository
import com.example.profile.domain.DeleteUserUseCase
import com.example.profile.domain.UpdateUserUseCase
import com.example.profile.domain.UserByIdUseCase
import com.example.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(get(), get(), get()) }
    factory { provideDeleteUserUseCase(get()) }
    factory { provideUpdateUserUseCase(get()) }
    factory { provideUserByIdUseCase(get()) }
}

private fun provideDeleteUserUseCase(
    userRepository: UserRepository,
): DeleteUserUseCase = DeleteUserUseCase(userRepository)

private fun provideUpdateUserUseCase(
    userRepository: UserRepository,
): UpdateUserUseCase = UpdateUserUseCase(userRepository)

private fun provideUserByIdUseCase(
    userRepository: UserRepository,
): UserByIdUseCase = UserByIdUseCase(userRepository)

