package com.example.registration.presentation.viewmodel

import androidx.compose.runtime.Immutable
import com.example.database.data.entity.UserEntity

@Immutable
data class UserScreenState(
    val error: Throwable? = null,
    val userInfo: UserEntity? = null
)