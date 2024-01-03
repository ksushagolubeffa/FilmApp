package com.example.profile.presentation.viewmodel

sealed interface ProfileScreenEvent {
    //    data class OnCreate(val id: Int) : ScreenEvent
    data class OnUpdateUser(
        val id: Int,
        val email: String,
        val username: String,
        val password: ByteArray,
        val points: Int
    ): ProfileScreenEvent
    data class OnDeleteUser(val id: Int): ProfileScreenEvent
    data class OnLoadUserInfo(val id: Int): ProfileScreenEvent
}