package com.example.impl.presentation.screen

import android.os.Looper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.films.impl.R
import com.example.impl.presentation.viewmodel.FilmsScreenEvent
import com.example.impl.presentation.viewmodel.FilmsViewModel
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.Trace

import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavController) {

    val viewModel: FilmsViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    val trace: Trace = FirebasePerformance.getInstance().newTrace("network_call_trace")
    val filmsSetFlag = rememberSaveable { mutableStateOf(false) }
    val isLoading = rememberSaveable { mutableStateOf(false) }
    val expanded = rememberSaveable { mutableStateOf(false) }
    val selectedIndex = rememberSaveable { mutableStateOf(0) }
    val genres = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        14 to "Fantasy",
        36 to "History",
        10751 to "Family",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )
//    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
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
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = {
                    expanded.value = !expanded.value
                }
            ) {
                TextField(
                    value = if (selectedIndex.value != 0) {
                        genres[selectedIndex.value]!!
                    } else {
                        "Select genre"
                    },
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    for ((key, value) in genres) {
                        DropdownMenuItem(onClick = {
                            selectedIndex.value = key
                            expanded.value = false
                        }) {
                            Text(value)
                        }
                    }
                }
            }
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            for ((key, value) in genres) {
                DropdownMenuItem(onClick = {
                    selectedIndex.value = key
                    expanded.value = false
                }) {
                    Text(value)
                }
            }
        }

        Button(onClick = {
            isLoading.value = true
            if (selectedIndex.value != 0) {
                trace.start()
                viewModel.reducer(FilmsScreenEvent.OnLoadListInfo(selectedIndex.value))
                val handler = android.os.Handler(Looper.getMainLooper())
                handler.postDelayed({
                    if (state.value.filmListInfo != null) {
                        filmsSetFlag.value = true
                    }
                }, 2000)
                trace.stop()
            }
        }, modifier = Modifier.padding(top= 8.dp, start = 32.dp)) {
            Text("Search films")
        }

        if(isLoading.value){
            LoadingScreen()
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (filmsSetFlag.value) {
            Log.e("screen", state.value.filmListInfo!![0].toString())
            RecyclerViewItems(filmList = state.value.filmListInfo, navController = navController)
            isLoading.value=false
        }

    }
}

@Composable
private fun RecyclerViewItems(
    filmList: List<com.example.api.model.FilmListModel>?,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
    ) {
        items(filmList!!.size) { index ->
            MyListItem(navController = navController, detailModel = filmList[index])
        }
    }
}

@Composable
private fun MyListItem(
    navController: NavController,
    detailModel: com.example.api.model.FilmListModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    "detailfilm_screen/${detailModel.id}/${false}"
                )
            }
            .padding(8.dp)
            .background(
                color = Color(0xFFF1E9E9),
            )
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
        ) {
            AsyncImage(
                model = detailModel.poster_path,
                placeholder = painterResource(R.drawable.default_preview),
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


