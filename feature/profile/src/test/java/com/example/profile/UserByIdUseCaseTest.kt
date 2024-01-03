package com.example.profile

import com.example.database.data.entity.UserEntity
import com.example.database.data.repository.UserRepository
import com.example.profile.domain.UserByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.test.runTest
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class UserByIdUseCaseTest {

    @MockK
    lateinit var userRepository: UserRepository

    private lateinit var getUserByIdUseCase: UserByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUserByIdUseCase = UserByIdUseCase(userRepository)
    }

    @Test
    fun whenGetUserByIdUseCaseExpectedSuccess() {
        val passw = "k464350g"
        val requestId = 0
        val expectedData: UserEntity = mockk {
            every { id } returns 37
            every { email } returns "ksusha-golubeffa@mail.ru"
            every { username} returns "ksushagolubeffa"
            every { password } returns passw.toByteArray()
            every { points } returns 0

        }
        coEvery {
            userRepository.getById(requestId)
        } returns expectedData
        runTest {
            val result = getUserByIdUseCase.invoke(id = requestId)
            assertEquals(expectedData, result)
        }
    }

    @Test
    fun whenGetUserByIdUseCaseExpectedError() {
        val requestId = 170
        coEvery {
            userRepository.getById(requestId)
        } throws RuntimeException()
        runTest {
            assertFailsWith<RuntimeException> {
                getUserByIdUseCase.invoke(id = requestId)
            }
        }
    }
}