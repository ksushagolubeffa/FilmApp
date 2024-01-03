package com.example.profile.domain

import com.example.database.data.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateUserUseCase(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(id: Int, email: String, username: String, password: ByteArray, points: Int){
        return withContext(dispatcher) {
            repository.updateUser(id, email, username, password, points)
        }
    }
}