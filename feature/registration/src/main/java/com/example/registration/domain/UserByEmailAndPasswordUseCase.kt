package com.example.registration.domain

import com.example.database.data.entity.UserEntity
import com.example.database.data.repository.UserRepository

class UserByEmailAndPasswordUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(email: String, password: ByteArray): UserEntity?{
        return repository.getByEmailAndPassword(email, password)
    }
}