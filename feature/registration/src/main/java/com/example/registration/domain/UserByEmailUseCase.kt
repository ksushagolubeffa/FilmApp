package com.example.registration.domain

import com.example.database.data.entity.UserEntity
import com.example.database.data.repository.UserRepository

class UserByEmailUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(email: String): UserEntity?{
        return repository.getByEmail(email)
    }
}