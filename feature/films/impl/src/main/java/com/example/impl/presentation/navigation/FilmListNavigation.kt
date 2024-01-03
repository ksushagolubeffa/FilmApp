package com.example.impl.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.impl.presentation.screen.FilmListScreen

fun NavGraphBuilder.filmListScreen(navController: NavHostController) {
    composable("films_screen") {
        FilmListScreen(navController = navController)
    }
}