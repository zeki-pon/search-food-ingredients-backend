package com.sfi.backend.service.food

import com.sfi.backend.domain.model.Food
import com.sfi.backend.domain.repository.FoodRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class FoodService(
    private val foodRepository: FoodRepository
) {
    fun createFood(food: Food): Food {
        foodRepository.create(food)

        return foodRepository.findByIdAndUserId(food.id, food.userId)!!
    }

    fun getList(userId: String): List<Food> {
        return foodRepository.findByUserId(userId)
    }

    fun getDetail(foodId: String, userId: String): Food {
        return foodRepository.findByIdAndUserId(foodId, userId)
            ?: throw IllegalArgumentException("This food data is not found: food_id=${foodId}")
    }

    fun updateFood(food: Food): Food {
        foodRepository.findByIdAndUserId(food.id, food.userId)
            ?: throw IllegalArgumentException("This food data is not found: food_id=${food.id}")

        foodRepository.update(food)

        return foodRepository.findByIdAndUserId(food.id, food.userId)!!
    }

    fun deleteFood(foodId: String, userId: String): Food {
        val food = foodRepository.findByIdAndUserId(foodId, userId)
            ?: throw IllegalArgumentException("This food data is not found: food_id=${foodId}")

        foodRepository.delete(foodId, userId)

        return food
    }
}