package com.example.network

import com.example.network.BuildConfig.API_ENDPOINT
import com.example.network.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    factory<Interceptor>(named("Logging")) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    single { GsonConverterFactory.create() }
    single(qualifier = named("base_url")) { API_ENDPOINT }
    single {
        provideHttpClient(get(named("Logging")))
    }
    single {
        provideRetrofit(
            httpClient = get(),
            gsonFactory = get(),
            baseUrl = get(named("base_url"))
        )
    }
}

private fun provideHttpClient(
    loggingInterceptor: Interceptor,
): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", API_KEY)
            .addHeader("X-RapidAPI-Host", "advanced-movie-search.p.rapidapi.com")
            .build()
        chain.proceed(request)
    }
    .connectTimeout(10L, TimeUnit.SECONDS)
    .readTimeout(10L, TimeUnit.SECONDS)
    .writeTimeout(10L, TimeUnit.SECONDS)
    .build()


private fun provideRetrofit(
    httpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory,
    baseUrl: String,
): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addConverterFactory(gsonFactory)
    .build()

