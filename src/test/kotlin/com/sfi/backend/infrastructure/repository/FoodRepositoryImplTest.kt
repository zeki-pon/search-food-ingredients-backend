package com.sfi.backend.infrastructure.repository

import com.sfi.backend.domain.model.Food
import com.sfi.backend.domain.model.User
import com.sfi.backend.domain.repository.FoodRepository
import com.sfi.backend.domain.repository.UserRepository
import com.sfi.backend.test.configuration.AbstractDBTest
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FoodRepositoryImplTest @Autowired constructor(
    private val foodRepository: FoodRepository,
    private val userRepository: UserRepository
): AbstractDBTest() {

    @BeforeEach
    fun setUp() {
        Flyway.configure()
            .dataSource(getContainer().jdbcUrl, getContainer().username, getContainer().password)
            .load()
            .migrate()
    }

    @BeforeAll
    fun setUpData() {
        userRepository.create(User(
            id = "usr_food_test",
            name = "test-user",
            email = "test-food@test.co.jp",
            password = "password"
        ))
    }

    @Test
    fun test() {
        val food = Food(
            id = "food_12345",
            userId = "usr_food_test",
            name = "test-food",
            calories = 100,
            protein = 20,
            fat = 5,
            carbohydrate = 30,
            perGrams = 100
        )

        // create
        assertDoesNotThrow{
            foodRepository.create(food)
        }

        // find by id
        var findByIdResult = foodRepository.findByIdAndUserId("food_12345", "usr_food_test")
        assertEquals("food_12345", findByIdResult!!.id)
        assertEquals("usr_food_test", findByIdResult.userId)
        assertEquals("test-food", findByIdResult.name)
        assertEquals(100, findByIdResult.calories)
        assertEquals(20, findByIdResult.protein)
        assertEquals(5, findByIdResult.fat)
        assertEquals(30, findByIdResult.carbohydrate)
        assertEquals(100, findByIdResult.perGrams)

        // find by user_id
        assertEquals(1, foodRepository.findByUserId("usr_food_test").size)

        // update
        assertDoesNotThrow {
            foodRepository.update(food.copy(protein = 100))
        }

        // find by id
        findByIdResult = foodRepository.findByIdAndUserId("food_12345", "usr_food_test")
        assertEquals("food_12345", findByIdResult!!.id)
        assertEquals("usr_food_test", findByIdResult.userId)
        assertEquals("test-food", findByIdResult.name)
        assertEquals(100, findByIdResult.calories)
        assertEquals(100, findByIdResult.protein)
        assertEquals(5, findByIdResult.fat)
        assertEquals(30, findByIdResult.carbohydrate)
        assertEquals(100, findByIdResult.perGrams)

        // delete
        assertDoesNotThrow {
            foodRepository.delete("food_12345", "usr_food_test")
        }

        assertNull(foodRepository.findByIdAndUserId("food_12345", "usr_food_test"))
        assertTrue(foodRepository.findByUserId("usr_food_test").isEmpty())
    }
}