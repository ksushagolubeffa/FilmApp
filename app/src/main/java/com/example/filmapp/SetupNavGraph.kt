package com.example.filmapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.impl.presentation.favorite.navigation.favoriteScreen
import com.example.impl.presentation.film.navigation.detailFilmScreen
import com.example.impl.presentation.navigation.filmListScreen
import com.example.profile.presentation.navigation.editProfileScreen
import com.example.profile.presentation.navigation.profileScreen
import com.example.registration.presentation.navigation.signInScreen
import com.example.registration.presentation.navigation.signUpScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signup_screen") {
        filmListScreen(navController)
        detailFilmScreen()
        favoriteScreen(navController)
        editProfileScreen(navController)
        profileScreen(navController)
        signUpScreen(navController)
        signInScreen(navController)

    }
}
