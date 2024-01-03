package com.example.registration.presentation.screen

import android.os.Looper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.registration.presentation.viewmodel.AuthViewModel
import com.example.registration.presentation.viewmodel.UserScreenEvent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(navController: NavController) {

    val viewModel: AuthViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val passwordRepeatVisible = rememberSaveable { mutableStateOf(false) }

    val username = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val repeatPassword = rememberSaveable { mutableStateOf("") }
    val showErrorSnackbar = remember { mutableStateOf(false) }

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
                .padding(start = 10.dp, end = 4.dp, top = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Password",
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
                singleLine = true,
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        Icons.Filled.Create
                    else Icons.Filled.Lock

                    val description = if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                        Icon(imageVector  = image, description)
                    }
                },
                value = password.value,
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                onValueChange = { password.value = it },
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Repeat password",
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
                value = repeatPassword.value,
                singleLine = true,
                visualTransformation = if (passwordRepeatVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                onValueChange = { repeatPassword.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordRepeatVisible.value)
                        Icons.Filled.Create
                    else Icons.Filled.Lock
                    val description = if (passwordRepeatVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {passwordRepeatVisible.value = !passwordRepeatVisible.value}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            if (repeatPassword.value.isNotEmpty()) {
                if (repeatPassword.value != password.value) {
                    Text(
                        text = "Passwords are not similar",
                        color = Color.Red,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {
                    Text(
                        text = "Passwords are similar",
                        color = Color.Green,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
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
//                    Log.e("screen before set", user.toString())
                    viewModel.reducer(UserScreenEvent.OnCompareUser(email.value))
                    Log.e("screen before set", state.value.userInfo.toString())
                    val handler = android.os.Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        if (state.value.userInfo != null) {
                            showErrorSnackbar.value = true
                        } else {
                            Log.e("screen before set", state.value.userInfo.toString())
                            viewModel.reducer(
                                UserScreenEvent.OnCreateUser(
                                    email.value,
                                    username.value,
                                    password.value
                                )
                            )
                            Log.e("email before set", email.value)
                            handler.postDelayed({
                                viewModel.reducer(UserScreenEvent.OnCompareUser(email.value))
                                handler.postDelayed({
                                    Log.e("email after set", email.value)
                                    Log.e("screen after set", state.value.userInfo.toString())
                                    Firebase.analytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, null)
                                    navController.navigate("profile_screen/${state.value.userInfo?.id}")
                                }, 2000)
                            }, 2000)
                        }
                    }, 2000)

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF626161),
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ClickableText(
                text = AnnotatedString("Sign In"),
                onClick = {
                    navController.navigate("signin_screen")
                }
            )

        }

    }

    if (showErrorSnackbar.value) {
        LaunchedEffect(true) {
            delay(1000)
            showErrorSnackbar.value = false
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Red,
            ) {
                Text(
                    text = "User with this email or password already exist.",
                    color = Color.White
                )
            }
        }
    }
}
