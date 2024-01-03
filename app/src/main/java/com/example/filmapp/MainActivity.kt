package com.example.filmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.current_user.SharedPreferencesUtil
import com.example.filmapp.ui.theme.FilmAppTheme
import com.example.ui.BottomNavItem
import com.example.ui.BottomNavigationBar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, "my screen class")
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "my custom screen name")
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        setContent {
            FilmAppTheme {
                SetupCrashlytics()
                val navController = rememberNavController()

                val context = LocalContext.current
                val sharedPreferencesUtil = SharedPreferencesUtil(context)
                val id = sharedPreferencesUtil.getValue("current_user", -1)

                Scaffold(
                    bottomBar = {
                        if (currentRoute(navController) != "signin_screen" &&
                            currentRoute(navController) != "signup_screen"
                        ) {
                            BottomNavigationBar(items = listOf(

                                BottomNavItem("Films", "films_screen", Icons.Default.Movie),

                                BottomNavItem(
                                    "Favorite",
                                    "favorite_screen",
                                    Icons.Default.Bookmark
                                ),
                                BottomNavItem(
                                    "Profile",
                                    "profile_screen/${id}",
                                    Icons.Default.Person
                                )

                            ), navController = navController, onItemClick = {
                                navController.popBackStack()
                                navController.navigate(it.route)
                            })
                        }
                    }
                ) {
                    SetupNavGraph(navController)
                }
            }
        }
    }

    @Composable
    private fun currentRoute(navController: NavController): String? {
        return navController.currentBackStackEntryAsState().value?.destination?.route
    }
}
