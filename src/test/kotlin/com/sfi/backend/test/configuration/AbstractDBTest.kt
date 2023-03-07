package com.sfi.backend.test.configuration

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource

abstract class AbstractDBTest {
    companion object {
        @JvmStatic
        private val postgresqlContainer = TestPostgresqlContainer.getInstance().apply {
            withDatabaseName("unit-test-db")
            withUsername("postgres")
            withPassword("postgres")
        }

        init {
            postgresqlContainer.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun setProperty(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresqlContainer::getUsername)
            registry.add("spring.datasource.password", postgresqlContainer::getPassword)
        }

        fun getContainer(): TestPostgresqlContainer {
            return postgresqlContainer
        }
    }
}