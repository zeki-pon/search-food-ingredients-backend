package com.sfi.backend.domain.repository

import com.sfi.backend.domain.model.Food

interface FoodRepository {
    fun create(food: Food)

    fun findByIdAndUserId(id: String, userId: String): Food?

    fun findByUserId(userId: String): List<Food>

    fun update(food: Food)

    fun delete(id: String, userId: String)
}