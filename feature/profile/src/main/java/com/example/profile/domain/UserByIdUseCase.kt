package com.example.profile.domain

import com.example.database.data.entity.UserEntity
import com.example.database.data.repository.UserRepository

class UserByIdUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(id: Int): UserEntity? {
        return repository.getById(id)
    }
}