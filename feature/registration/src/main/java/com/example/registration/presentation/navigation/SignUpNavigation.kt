package com.example.registration.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.registration.presentation.screen.SignUpScreen

fun NavGraphBuilder.signUpScreen(navController: NavHostController) {
    composable("signup_screen") {
        SignUpScreen(navController = navController)
    }
}