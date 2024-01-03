package com.example.filmapp

import android.app.Application
import com.example.database.di.databaseModule
import com.example.favorite_database.di.favoriteDatabaseModule
import com.example.impl.di.favoriteModule
import com.example.impl.di.filmModule
import com.example.impl.di.filmsModule
import com.example.network.networkModule
import com.example.profile.di.profileModule
import com.example.registration.di.authModule
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.FirebasePerformance
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                databaseModule,
                authModule,
                profileModule,
                networkModule,
                filmsModule,
                filmModule,
                favoriteModule,
                favoriteDatabaseModule,
            )
        }
        FirebasePerformance.getInstance().isPerformanceCollectionEnabled = true
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}
