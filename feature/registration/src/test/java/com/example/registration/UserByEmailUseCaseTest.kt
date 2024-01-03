package com.example.registration

import com.example.database.data.entity.UserEntity
import com.example.database.data.repository.UserRepository
import com.example.registration.domain.UserByEmailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class UserByEmailUseCaseTest {

    @MockK
    lateinit var userRepository: UserRepository

    private lateinit var getUserByEmailUseCase: UserByEmailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUserByEmailUseCase = UserByEmailUseCase(userRepository)
    }

    @Test
    fun whenGetUserByEmailUseCaseExpectedSuccess() {
        val passw = "k464350g"
        val requestEmail = "ksusha-golubeffa@mail.ru"
        val expectedData: UserEntity = mockk {
            every { id } returns 37
            every { email } returns "ksusha-golubeffa@mail.ru"
            every { username} returns "ksushagolubeffa"
            every { password } returns passw.toByteArray()
            every { points } returns 0

        }
        coEvery {
            userRepository.getByEmail(requestEmail)
        } returns expectedData
        runTest {
            val result = getUserByEmailUseCase.invoke(requestEmail)
            TestCase.assertEquals(expectedData, result)
        }
    }

    @Test
    fun whenGetUserByEmailUseCaseExpectedError() {
        val requestEmail = "dfjgshhd@mail.ru"
        coEvery {
            userRepository.getByEmail(requestEmail)
        } throws RuntimeException()
        runTest {
            assertFailsWith<RuntimeException> {
                getUserByEmailUseCase.invoke(requestEmail)
            }
        }
    }
}