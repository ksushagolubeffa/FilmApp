package com.example.registration.domain

import android.util.Log
import com.example.database.data.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserUseCase(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(email: String, username: String, password: ByteArray){
        return withContext(dispatcher) {
            Log.e("create user", "use case")
            repository.createUser(email, username, password)
        }
    }
}