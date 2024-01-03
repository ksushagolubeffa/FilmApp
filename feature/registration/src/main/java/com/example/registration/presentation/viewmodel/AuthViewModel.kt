package com.example.registration.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.domain.CreateUserUseCase
import com.example.registration.domain.UserByEmailAndPasswordUseCase
import com.example.registration.domain.UserByEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val userByEmailAndPasswordUseCase: UserByEmailAndPasswordUseCase,
    private val userByEmailUseCase: UserByEmailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserScreenState())
    val state: StateFlow<UserScreenState> = _state.asStateFlow()

    private fun createUser(email: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                Log.e("create user, view model", "$email $username $password")
                createUserUseCase(email, username, password.toByteArray())
                Log.e("created user", "view model")
            } catch (error: Throwable) {
                _state.emit(
                    _state.value.copy(
                        error = error
                    )
                )
                Log.e("error in creating", error.toString())

            }
        }
    }

    private fun isUserAuth(email: String, password: String) {
        viewModelScope.launch {
            try {
                userByEmailAndPasswordUseCase(email, password.toByteArray()).also { userInfo ->
                    _state.emit(
                        _state.value.copy(
                            userInfo = userInfo
                        )
                    )
                }

            } catch (error: Throwable) {
                _state.emit(
                    _state.value.copy(
                        error = error
                    )
                )

            }
        }
    }

    private fun getUserByEmail(email: String) {
        viewModelScope.launch {
            try {
                Log.e("compare emails, view model", email)
                val userInfo = userByEmailUseCase(email)
                _state.emit(
                    _state.value.copy(
                        userInfo = userInfo
                    )
                )
                Log.e("compared", state.value.toString())
            } catch (error: Throwable) {
                _state.emit(
                    _state.value.copy(
                        error = error
                    )
                )
                Log.e("error in comparing", error.toString())
            }
        }
    }

    fun reducer(event: UserScreenEvent) {
        when (event) {
            is UserScreenEvent.OnAuthUser -> isUserAuth(event.email, event.password)
            is UserScreenEvent.OnCreateUser -> createUser(
                event.email,
                event.username,
                event.password
            )

            is UserScreenEvent.OnCompareUser -> getUserByEmail(event.email)
        }
    }

}
