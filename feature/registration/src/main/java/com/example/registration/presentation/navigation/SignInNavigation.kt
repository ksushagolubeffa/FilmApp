package com.example.registration.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.registration.presentation.screen.SignInScreen

fun NavGraphBuilder.signInScreen(navController: NavHostController) {
    composable("signin_screen") {
        SignInScreen(navController = navController)
    }
}