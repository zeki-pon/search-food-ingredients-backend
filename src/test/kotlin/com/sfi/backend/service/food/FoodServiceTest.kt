package com.sfi.backend.service.food

import com.sfi.backend.domain.model.Food
import com.sfi.backend.domain.repository.FoodRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class FoodServiceTest {
    private val foodRepository = mock<FoodRepository>()

    private val foodService = FoodService(foodRepository)

    @Test
    fun givenAsFood_whenExecutingCreateFoodFunction_thenReturnFood() {
        // expected
        val expected = Food(
            id = "food_12345",
            userId = "user_12345",
            name = "test_food",
            calories = 999,
            protein = 50,
            fat = 20,
            carbohydrate = 10,
            perGrams = 100,
        )

        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(expected)

        // given
        val food = expected.copy()

        // when
        val result = foodService.createFood(food)

        // then
        Assertions.assertEquals("food_12345", result.id)
        Assertions.assertEquals("user_12345", result.userId)
        Assertions.assertEquals("test_food", result.name)
        Assertions.assertEquals(999, result.calories)
        Assertions.assertEquals(50, result.protein)
        Assertions.assertEquals(20, result.fat)
        Assertions.assertEquals(10, result.carbohydrate)
        Assertions.assertEquals(100, result.perGrams)
    }

    @Test
    fun givenAsUserId_whenExecutingGetListFunction_thenReturnFoodList() {
        // expected
        val expected = listOf(
            Food(
                id = "food_12345",
                userId = "user_12345",
                name = "test_food",
                calories = 999,
                protein = 50,
                fat = 20,
                carbohydrate = 10,
                perGrams = 100,
            )
        )

        // mock
        whenever(foodRepository.findByUserId(any())).thenReturn(expected)

        // given
        val userId = "user_12345"

        // when
        val result = foodService.getList(userId)

        // then
        Assertions.assertTrue(result.isNotEmpty())
        Assertions.assertEquals(1, result.size)
    }

    @Test
    fun givenAsFoodId_whenExecutingGetDetailFunction_thenReturnFood() {
        // expected
        val expected = Food(
            id = "food_12345",
            userId = "user_12345",
            name = "test_food",
            calories = 999,
            protein = 50,
            fat = 20,
            carbohydrate = 10,
            perGrams = 100,
        )

        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(expected)

        // given
        val foodId = "food_12345"
        val userId = "user_12345"

        // when
        val result = foodService.getDetail(foodId, userId)

        // then
        Assertions.assertEquals("food_12345", result.id)
        Assertions.assertEquals("user_12345", result.userId)
        Assertions.assertEquals("test_food", result.name)
        Assertions.assertEquals(999, result.calories)
        Assertions.assertEquals(50, result.protein)
        Assertions.assertEquals(20, result.fat)
        Assertions.assertEquals(10, result.carbohydrate)
        Assertions.assertEquals(100, result.perGrams)
    }

    @Test
    fun givenAsNonExistentFoodId_whenExecutingGetDetailFunction_thenThrowException() {
        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(null)

        // given
        val foodId = "food_12345"
        val userId = "user_12345"

        // when
        val result = Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) {
            foodService.getDetail(foodId, userId)
        }

        // then
        Assertions.assertEquals("This food data is not found: food_id=${foodId}", result.message)
    }

    @Test
    fun givenAsFood_whenExecutingUpdateFoodFunction_thenReturnFood() {
        // expected
        val expected = Food(
            id = "food_12345",
            userId = "user_12345",
            name = "test_food",
            calories = 999,
            protein = 50,
            fat = 20,
            carbohydrate = 10,
            perGrams = 100,
        )

        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(expected)

        // given
        val food = expected.copy()

        // when
        val result = foodService.updateFood(food)

        // then
        Assertions.assertEquals("food_12345", result.id)
        Assertions.assertEquals("user_12345", result.userId)
        Assertions.assertEquals("test_food", result.name)
        Assertions.assertEquals(999, result.calories)
        Assertions.assertEquals(50, result.protein)
        Assertions.assertEquals(20, result.fat)
        Assertions.assertEquals(10, result.carbohydrate)
        Assertions.assertEquals(100, result.perGrams)
    }

    @Test
    fun givenAsNonExistentFood_whenExecutingUpdateFoodFunction_thenThrowException() {
        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(null)

        // given
        val food = Food(
            id = "food_12345",
            userId = "user_12345",
            name = "test_food",
            calories = 999,
            protein = 50,
            fat = 20,
            carbohydrate = 10,
            perGrams = 100,
        )

        // when
        val result = Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) {
            foodService.updateFood(food)
        }

        // then
        Assertions.assertEquals("This food data is not found: food_id=${food.id}", result.message)
    }

    @Test
    fun givenAsNFoodId_whenExecutingDeleteFoodFunction_thenReturnFood() {
        // expected
        val expected = Food(
            id = "food_12345",
            userId = "user_12345",
            name = "test_food",
            calories = 999,
            protein = 50,
            fat = 20,
            carbohydrate = 10,
            perGrams = 100,
        )

        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(expected)

        // given
        val foodId = "food_12345"
        val userId = "user_12345"

        // when
        val result = foodService.deleteFood(foodId, userId)

        // then
        Assertions.assertEquals("food_12345", result.id)
        Assertions.assertEquals("user_12345", result.userId)
        Assertions.assertEquals("test_food", result.name)
        Assertions.assertEquals(999, result.calories)
        Assertions.assertEquals(50, result.protein)
        Assertions.assertEquals(20, result.fat)
        Assertions.assertEquals(10, result.carbohydrate)
        Assertions.assertEquals(100, result.perGrams)
    }

    @Test
    fun givenAsNonExistentFoodId_whenExecutingDeleteFoodFunction_thenThrowException() {
        // mock
        whenever(foodRepository.findByIdAndUserId(any(), any())).thenReturn(null)

        // given
        val foodId = "food_12345"
        val userId = "user_12345"

        // when
        val result = Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) {
            foodService.deleteFood(foodId, userId)
        }

        // then
        Assertions.assertEquals("This food data is not found: food_id=${foodId}", result.message)
    }
}