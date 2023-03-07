package com.sfi.backend.test.configuration

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class TestPostgresqlContainer private constructor(
    private val dockerImageName: DockerImageName
): PostgreSQLContainer<TestPostgresqlContainer>(dockerImageName) {

    companion object {
        private const val IMAGE_VERSION = "postgres:14.7"
        private var container: TestPostgresqlContainer? = null

        fun getInstance(image: String = IMAGE_VERSION): TestPostgresqlContainer {
            if (container == null) {
                container = TestPostgresqlContainer(DockerImageName.parse(image))
            }
            return container!!
        }
    }

    override fun start() {
        super.start()
    }

    override fun stop() {}
}