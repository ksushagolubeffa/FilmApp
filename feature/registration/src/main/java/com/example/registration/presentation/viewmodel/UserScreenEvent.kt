package com.example.registration.presentation.viewmodel

sealed interface UserScreenEvent {
    data class OnCreateUser(val email: String, val username: String, val password: String):
        UserScreenEvent
    data class OnAuthUser(val email: String, val password: String): UserScreenEvent
    data class OnCompareUser(val email: String): UserScreenEvent
}