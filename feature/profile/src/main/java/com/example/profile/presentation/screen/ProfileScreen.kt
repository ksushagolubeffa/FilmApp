package com.example.profile.presentation.screen

import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.current_user.SharedPreferencesUtil
import com.example.database.data.entity.UserEntity
import com.example.profile.presentation.viewmodel.ProfileScreenEvent
import com.example.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    id: Int,
) {
    val viewModel: ProfileViewModel = koinViewModel()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val userSetFlag = rememberSaveable { mutableStateOf(false) }

    viewModel.reducer(ProfileScreenEvent.OnLoadUserInfo(id))
    LoadingScreen()
    val handler = android.os.Handler(Looper.getMainLooper())
    handler.postDelayed({
        if (state.value.userInfo != null) {
            userSetFlag.value = true
        }
    }, 2000)
    Log.e("profile info", state.value.userInfo.toString())
    Log.e("id in profile", id.toString())
    if (userSetFlag.value) {
        val context = LocalContext.current
        val sharedPreferencesUtil = SharedPreferencesUtil(context)
        sharedPreferencesUtil.setValue("current_user", id)
        ProfileContent(
            user = state.value.userInfo,
            navController = navController,
            viewModel = viewModel
        )
    }

}

@Composable
fun ProfileContent(
    user: UserEntity?,
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8D7EF),
                        Color(0xFFC1D5F5)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Username",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp)
            )
            Text(
                text = user!!.username,
                style = MaterialTheme.typography.body2,
                color = Color(0xFF626161),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(start = 10.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(start = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Email",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp)
            )
            Text(
                text = user!!.email,
                style = MaterialTheme.typography.body2,
                color = Color(0xFF626161),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(start = 10.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(start = 10.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 40.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick =
                    {
                        navController.navigate(
                            "editprofile_screen/${user!!.id}"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF728EB1)
                    ),
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = "Change",
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFFFFFFFF),
                    )
                }

                Button(
                    onClick =
                    {
                        val sharedPreferences =
                            context.getSharedPreferences("app_shared_prefs", Context.MODE_PRIVATE)
                        sharedPreferences.edit().remove("current_user").apply()
                        navController.navigate(
                            "signin_screen"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = "Exit",
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFF626161),
                    )
                }



                Button(
                    onClick =
                    {
                        showDialog.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFB17272)
                    ),
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = "Delete account",
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFFFFF6F6),
                    )
                }

            }
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Delete Profile") },
                text = { Text("Are you sure you want to delete your profile?") },
                confirmButton = {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White
                            ), onClick = { showDialog.value = false }) {
                                Text(text = "Cancel",
                                    style = MaterialTheme.typography.body2,
                                )
                            }
                            Button(colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFB17272)
                            ),
                                onClick = {
                                    viewModel.reducer(ProfileScreenEvent.OnDeleteUser(user!!.id))
                                    val sharedPreferences = context.getSharedPreferences(
                                        "app_shared_prefs",
                                        Context.MODE_PRIVATE
                                    )
                                    sharedPreferences.edit().remove("current_user").apply()
                                    val handler = android.os.Handler(Looper.getMainLooper())
                                    handler.postDelayed({
                                        navController.navigate(
                                            "signup_screen"
                                        )
                                    }, 2000)
                                }) {
                                Text(text = "Delete",
                                    style = MaterialTheme.typography.body2,
                                    color = Color(0xFFFFFFFF),)
                            }
                        }
                    }
                }
            )
        }
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