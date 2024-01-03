package com.example.database.data

import android.util.Log
import com.example.database.data.entity.UserEntity
import com.example.database.data.model.User
import com.example.database.data.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {
    override suspend fun getById(id: Int): UserEntity? {
        return getUser(userDao.getById(id))
    }

    override suspend fun getByEmail(email: String): UserEntity? {
        Log.e("compare emails", email)
        val ans = userDao.getByEmail(email)
        Log.e("compared", ans.toString())
        return getUser(ans)
    }

    override suspend fun getByEmailAndPassword(email: String, password: ByteArray): UserEntity? {
        return getUser(userDao.getUser(email, password))
    }

    override suspend fun deleteUser(id: Int) {
        userDao.delete(id)
    }

    override suspend fun createUser(email: String, username: String, password: ByteArray) {
        val user = User(0, email, username, password, 0)
        userDao.add(user)
    }

    override suspend fun updateUser(
        id: Int,
        email: String,
        username: String,
        password: ByteArray,
        points: Int
    ) {
        val user = User(id, email, username, password, points)
        userDao.update(user)
    }

    private fun getUser(user: User?): UserEntity?{
        return if(user==null) null
        else UserEntity(user.id, user.email, user.username, user.password, user.points)
    }
}
