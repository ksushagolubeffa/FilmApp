package com.example.impl.presentation.favorite.screen

import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.api.model.favorite.FavoriteEntity
import com.example.favorites.impl.R
import com.example.impl.presentation.favorite.viewmodel.FavScreenEvent
import com.example.impl.presentation.favorite.viewmodel.FavViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(navController: NavController) {

    val viewModel: FavViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val filmsSetFlag = rememberSaveable { mutableStateOf(false) }
    val emptyList = rememberSaveable { mutableStateOf(false) }

    viewModel.reducer(FavScreenEvent.OnGetAllFavorite(10))
    LoadingScreen()
    val handler = android.os.Handler(Looper.getMainLooper())
    handler.postDelayed({
        if (state.value.filmList != null && state.value.filmList!!.isNotEmpty()) {
            filmsSetFlag.value = true
            Log.e("fav screen list", state.value.filmList.toString())
            Log.e("fav screen list size", state.value.filmList!!.size.toString())


        }
    }, 2000)

    if (filmsSetFlag.value) {
        RecyclerViewItems(filmList = state.value.filmList, navController = navController)
    } else {
        handler.postDelayed({
            emptyList.value = true
        }, 2000)
    }

    if (emptyList.value && !filmsSetFlag.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8D7EF),
                            Color(0xFFC1D5F5)
                        )
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicText(
                text = "You haven't added any films",
                style = TextStyle(color = Color.Black, fontSize = 20.sp)
            )
        }
    }

}

@Composable
private fun RecyclerViewItems(
    filmList: List<FavoriteEntity>?,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8D7EF),
                        Color(0xFFC1D5F5)
                    )
                )
            )
    ) {
        items(filmList!!.size) { index ->
            MyListItem(navController = navController, detailModel = filmList[index])
        }
    }
}

@Composable
private fun MyListItem(
    navController: NavController,
    detailModel: FavoriteEntity,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    "detailfilm_screen/${detailModel.id}/${true}"
                )
            }
            .padding(8.dp)
            .background(
                color = Color(0xFFFFFFFF),
            )
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.default_preview),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )

        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = detailModel.title,
            style = MaterialTheme.typography.body2,
            color = Color(0xFF626161),
            fontSize = 20.sp
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = detailModel.vote_average.toString(),
            style = MaterialTheme.typography.body2,
            color = Color(0xFF626161),
            fontSize = 20.sp
        )

    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}