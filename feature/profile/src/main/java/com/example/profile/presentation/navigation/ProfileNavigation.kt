package com.example.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.compose.runtime.remember
import androidx.navigation.navArgument
import com.example.profile.presentation.screen.ProfileScreen

fun NavGraphBuilder.profileScreen(navController: NavHostController) {
    composable("profile_screen/{id}", arguments = listOf(
        navArgument("id") { type = NavType.IntType }
    )) {
        val id = remember { it.arguments?.getInt("id") }
        ProfileScreen(navController = navController, id = id ?: -1)
    }
}