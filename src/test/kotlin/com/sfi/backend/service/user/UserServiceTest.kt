package com.sfi.backend.service.user

import com.sfi.backend.domain.model.User
import com.sfi.backend.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class UserServiceTest {
    private val userRepository = mock<UserRepository>()

    private val userService = UserService(userRepository)

    @Test
    fun givenAsUser_whenExecutingCreateUserFunction_thenReturnUser() {
        // expected
        val expected = User(
            id = "user_12345",
            name = "test user",
            email ="test@test.jp",
            password = "test12345",
        )

        // mock
        whenever(userRepository.findByEmail(any())).thenReturn(expected)

        // given
        val user = expected.copy()

        // when
        val result = userService.createUser(user)

        // then
        Assertions.assertEquals("user_12345", result.id)
        Assertions.assertEquals("test user", result.name)
        Assertions.assertEquals("test@test.jp", result.email)
        Assertions.assertEquals("test12345", result.password)
    }

    @Test
    fun givenAsExistentEmail_whenExecutingGetDetailFunction_thenReturnUser() {
        // expected
        val expected = User(
            id = "user_12345",
            name = "test user",
            email ="test@test.jp",
            password = "test12345",
        )

        // mock
        whenever(userRepository.findByEmail(any())).thenReturn(expected)

        // given
        val email = "test@test.jp"

        // when
        val result = userService.getDetail(email)

        // then
        Assertions.assertEquals("user_12345", result.id)
        Assertions.assertEquals("test user", result.name)
        Assertions.assertEquals("test@test.jp", result.email)
        Assertions.assertEquals("test12345", result.password)
    }

    @Test
    fun givenAsNonExistentEmail_whenExecutingGetDetailFunction_thenThrowException() {
        // mock
        whenever(userRepository.findByEmail(any())).thenReturn(null)

        // given
        val email = "test@test.jp"

        // when
        val result = Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) {
            userService.getDetail(email)
        }

        // then
        Assertions.assertEquals("This user data is not found", result.message)
    }
}