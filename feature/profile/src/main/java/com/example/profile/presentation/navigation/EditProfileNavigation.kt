package com.example.profile.presentation.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.profile.presentation.screen.EditProfileScreen

fun NavGraphBuilder.editProfileScreen(navController: NavHostController) {
    composable("editprofile_screen/{id}", arguments = listOf(
        navArgument("id") { type = NavType.IntType },
    )
    ) {
        val id = remember { it.arguments?.getInt("id") }
        EditProfileScreen(navController = navController, id = id ?: -1)
    }
}