package com.example.impl.presentation.film.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.impl.presentation.film.screen.DetailFilmScreen

fun NavGraphBuilder.detailFilmScreen() {
    composable("detailfilm_screen/{id}/{isLocal}", arguments = listOf(
        navArgument("id") { type = NavType.IntType },
        navArgument("isLocal") { type = NavType.BoolType }
    )) {
        val id = remember { it.arguments?.getInt("id") }
        val isLocal = remember { it.arguments?.getBoolean("isLocal") }
        DetailFilmScreen(
            id = id ?: -1,
            isLocal = isLocal ?: false
        )
    }
}