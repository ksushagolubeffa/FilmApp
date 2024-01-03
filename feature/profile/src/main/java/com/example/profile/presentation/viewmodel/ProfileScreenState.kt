package com.example.profile.presentation.viewmodel

import androidx.compose.runtime.Immutable
import com.example.database.data.entity.UserEntity

@Immutable
data class ProfileScreenState(
    val loading: Boolean = false,
    val userInfo: UserEntity? = null,
    val error: Throwable? = null,
)