package com.example.impl.presentation.film.screen

import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.api.model.favorite.FavoriteEntity
import com.example.api.model.film.FilmModel
import com.example.favorites.impl.R
import com.example.impl.presentation.favorite.screen.LoadingScreen
import com.example.impl.presentation.favorite.viewmodel.FavScreenEvent
import com.example.impl.presentation.favorite.viewmodel.FavViewModel
import com.example.impl.presentation.film.viewmodel.FilmDetailScreenEvent
import com.example.impl.presentation.film.viewmodel.FilmDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailFilmScreen(id: Int, isLocal: Boolean) {
    val viewModel: FilmDetailViewModel = koinViewModel()
    val favoriteViewModel: FavViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val stateFav = favoriteViewModel.state.collectAsStateWithLifecycle()
    val filmSetFlag = rememberSaveable { mutableStateOf(false) }
    val favSetFlag = rememberSaveable { mutableStateOf(false) }

    LoadingScreen()
    id.let {
        if (isLocal) {
            favoriteViewModel.reducer(FavScreenEvent.OnFindById(id))
        } else {
            viewModel.reducer(FilmDetailScreenEvent.OnLoadDetailInfo(id))
        }
    }
    val handler = android.os.Handler(Looper.getMainLooper())
    handler.postDelayed({
        if (state.value.filmInfo != null) {
            filmSetFlag.value = true
        }
        if (stateFav.value.filmInfo != null) {
            favSetFlag.value = true
        }
    }, 2000)
    if (filmSetFlag.value) {
        DetailFilmContent(
            detailModel = state.value.filmInfo,
            favViewModel = favoriteViewModel,
            isLocal
        )
    }
    if (favSetFlag.value) {
        DetailFilmContent(
            detailModel = getFilmModel(stateFav.value.filmInfo),
            favViewModel = favoriteViewModel,
            isLocal
        )
    }
}

@Composable
fun DetailFilmContent(detailModel: FilmModel?, favViewModel: FavViewModel, isFavorite: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8D7EF),
                        Color(0xFFC1D5F5)
                    )
                )
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            backgroundColor = Color(0xFFF1E9E9),
        )
        {
            AsyncImage(
                model = detailModel?.image!!,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        val bipmap = if (isFavorite) painterResource(id = R.drawable.fav) else painterResource(id = R.drawable.not_fav)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = detailModel?.title!!,
                style = MaterialTheme.typography.body2,
                color = Color(0xFF000000),
                fontSize = 24.sp
            )
            IconButton(
                onClick = {
                    if (isFavorite) {
                        favViewModel.reducer(FavScreenEvent.OnDeleteFavorite(detailModel?.id!!))
                    } else {
                        favViewModel.reducer(
                            FavScreenEvent.OnAddToFavorite(
                                detailModel.title,
                                detailModel.release_date,
                                detailModel.image,
                                detailModel.runtime,
                                detailModel.description,
                                detailModel.budget,
                                detailModel.genres,
                                detailModel.tagline,
                                detailModel.vote_average,
                                detailModel.production_companies,
                                detailModel.production_countries
                            )
                        )
                    }
                },
                modifier = Modifier
                    .wrapContentSize(Alignment.Center),
            ){
                Icon(bipmap, contentDescription = "Play button")
            }
        }
        Text(
            text = detailModel!!.tagline,
            style = MaterialTheme.typography.body2,
            color = Color(0xFF646464),
            fontSize = 22.sp
        )
        Text(
            text = detailModel!!.release_date,
            style = MaterialTheme.typography.body2,
            color = Color(0xFF626161),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))

        var genres = ""
        detailModel!!.genres.forEach {
            genres = if (genres == "") {
                it
            } else {
                "${genres}, $it"
            }
        }

        Row {
            Text(
                text = "Genres: ",
                color = Color(0xFF626161),
                textAlign = TextAlign.Start
            )
            Text(
                text = genres,
                color = Color(0xFF626161),
                modifier = Modifier
                    .alignByBaseline()
            )
        }

        var countries = ""
        detailModel!!.production_countries.forEach {
            countries = if (countries == "") {
                it
            } else {
                "${countries}, $it"
            }
        }

        Row {
            Text(
                text = "Countries: ",
                color = Color(0xFF626161),
                textAlign = TextAlign.Start
            )
            Text(
                text = countries,
                color = Color(0xFF626161),
                modifier = Modifier
                    .alignByBaseline()
            )
        }

        var companies = ""
        detailModel!!.production_companies.forEach {
            companies = if (companies == "") {
                it
            } else {
                "${companies}, $it"
            }
        }

        Row {
            Text(
                text = "Companies: ",
                color = Color(0xFF626161),
                textAlign = TextAlign.Start
            )
            Text(
                text = companies,
                color = Color(0xFF626161),
                modifier = Modifier
                    .alignByBaseline()
            )
        }


        Row {
            Text(
                text = "Time: ",
                color = Color(0xFF626161),
                textAlign = TextAlign.Start
            )
            Text(
                text = detailModel.runtime.toString(),
                color = Color(0xFF626161),
                modifier = Modifier
                    .alignByBaseline()
            )
        }


        Row {
            Text(
                text = "Rating: ",
                color = Color(0xFF626161),
                textAlign = TextAlign.Start
            )
            Text(
                text = detailModel.vote_average.toString(),
                color = Color(0xFF626161),
                modifier = Modifier
                    .alignByBaseline()
            )
        }


        Row {
            Text(
                text = "Budget: ",
                color = Color(0xFF626161),
                textAlign = TextAlign.Start
            )
            Text(
                text = detailModel!!.budget.toString(),
                color = Color(0xFF626161),
                modifier = Modifier
                    .alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = detailModel!!.description,
            style = MaterialTheme.typography.body2,
            color = Color(0xFF626161)
        )

        Spacer(modifier = Modifier.height(7.dp))
    }
}

fun getFilmModel(favorite: FavoriteEntity?): FilmModel? {
    return FilmModel(
        favorite?.id!!,
        favorite.title,
        favorite.release_date,
        favorite.image,
        favorite.runtime,
        favorite.description,
        favorite.budget,
        favorite.genres,
        favorite.tagline,
        favorite.vote_average,
        favorite.production_companies,
        favorite.production_countries,
    )
}