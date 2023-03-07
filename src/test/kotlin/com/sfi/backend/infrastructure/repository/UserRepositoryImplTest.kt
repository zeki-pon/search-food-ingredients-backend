package com.sfi.backend.infrastructure.repository

import com.sfi.backend.domain.model.User
import com.sfi.backend.domain.repository.UserRepository
import com.sfi.backend.test.configuration.AbstractDBTest
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserRepositoryImplTest @Autowired constructor(
    private val userRepository: UserRepository
): AbstractDBTest() {

    @BeforeEach
    fun setUp() {
        Flyway.configure()
            .dataSource(getContainer().jdbcUrl, getContainer().username, getContainer().password)
            .load()
            .migrate()
    }

    @Test
    fun test() {
        val user = User(
            id = "usr_12345",
            name = "test-user",
            email = "test@test.co.jp",
            password = "password"
        )

        assertDoesNotThrow{
            userRepository.create(user)
        }

        val result = userRepository.findByEmail("test@test.co.jp")
        assertEquals("usr_12345", result!!.id)
        assertEquals("test-user", result.name)
        assertEquals("test@test.co.jp", result.email)
        assertEquals("password", result.password)

        assertDoesNotThrow {
            userRepository.delete("usr_12345")
        }

        assertNull(userRepository.findByEmail("test@test.co.jp"))
    }
}