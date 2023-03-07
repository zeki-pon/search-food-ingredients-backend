package com.sfi.backend.domain.repository

import com.sfi.backend.domain.model.Food

interface FoodRepository {
    fun create(food: Food)

    fun findById(id: Long): Food?

    fun findByUserId(userId: String): List<Food>

    fun update(food: Food)

    fun delete(id: Long)
}