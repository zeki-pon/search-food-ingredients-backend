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
        foodRepository.create(food.copy(id = food.id))

        return foodRepository.findById(food.id)!!
    }

    fun getList(userId: String): List<Food> {
        return foodRepository.findByUserId(userId)
    }

    fun getDetail(foodId: String): Food {
        return foodRepository.findById(foodId)
            ?: throw IllegalArgumentException("This food data is not found: food_id=${foodId}")
    }

    fun updateFood(food: Food): Food {
        foodRepository.findById(food.id)
            ?: throw IllegalArgumentException("This food data is not found: food_id=${food.id}")

        foodRepository.update(food)

        return foodRepository.findById(food.id)!!
    }

    fun deleteFood(foodId: String): Food {
        val food = foodRepository.findById(foodId)
            ?: throw IllegalArgumentException("This food data is not found: food_id=${foodId}")

        foodRepository.delete(foodId)

        return food
    }
}