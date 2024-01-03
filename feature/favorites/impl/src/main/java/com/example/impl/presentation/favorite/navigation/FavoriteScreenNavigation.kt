package com.example.impl.presentation.favorite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.impl.presentation.favorite.screen.FavoriteScreen

fun NavGraphBuilder.favoriteScreen(navController: NavHostController) {
    composable("favorite_screen") {
        FavoriteScreen(navController = navController)
    }
}