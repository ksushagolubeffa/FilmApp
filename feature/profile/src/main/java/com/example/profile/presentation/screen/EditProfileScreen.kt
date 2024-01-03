package com.example.profile.presentation.screen

import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.database.data.entity.UserEntity
import com.example.profile.presentation.viewmodel.ProfileScreenEvent
import com.example.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
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
    if (userSetFlag.value) {
        EditProfileContent(
            user = state.value.userInfo,
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun EditProfileContent(
    user: UserEntity?,
    navController: NavController,
    viewModel: ProfileViewModel
) {

    val username = rememberSaveable {
        mutableStateOf(user!!.username)
    }

    val email = rememberSaveable {
        mutableStateOf(user!!.email)
    }

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
                .padding(start = 10.dp, end = 4.dp, top = 32.dp),
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
            TextField(
                modifier = Modifier
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = username.value,
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                onValueChange = { username.value = it },
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
            TextField(
                modifier = Modifier
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                value = email.value,
                onValueChange = { email.value = it },
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick =
                {
                    viewModel.reducer(
                        ProfileScreenEvent.OnUpdateUser(
                            user!!.id,
                            email.value,
                            username.value,
                            user.password,
                            user.points
                        )
                    )
                    val handler = android.os.Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        navController.navigate(
                            "profile_screen/${user.id}"
                        )
                    }, 2000)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Save changes",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF626161),
                )
            }

        }
    }
}