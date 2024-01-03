package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.data.UserDao
import com.example.database.data.UserDatabase
import com.example.database.data.repository.UserRepository
import com.example.database.data.UserRepositoryImpl
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDatabase(get()) }
    single { provideUserRepository(get()) }
    factory { provideUserDao(get()) }
}

private fun provideUserRepository(
    userDao: UserDao
): UserRepository = UserRepositoryImpl(userDao)

fun provideAppDatabase(context: Context): UserDatabase {
    return Room.databaseBuilder(context, UserDatabase::class.java, "users.db")
        .allowMainThreadQueries()
        .build()
}

fun provideUserDao(database: UserDatabase): UserDao {
    return database.getUserDao()
}

