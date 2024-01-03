package com.example.filmapp

import androidx.compose.runtime.Composable
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CustomUncaughtExceptionHandler(
    private val defaultExceptionHandler: Thread.UncaughtExceptionHandler?
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(throwable)
        defaultExceptionHandler?.uncaughtException(thread, throwable)
    }
}

@Composable
fun SetupCrashlytics() {
    // Set up a custom uncaught exception handler
    val currentHandler = Thread.getDefaultUncaughtExceptionHandler()
    if (currentHandler !is CustomUncaughtExceptionHandler) {
        Thread.setDefaultUncaughtExceptionHandler(
            CustomUncaughtExceptionHandler(currentHandler)
        )
    }
}