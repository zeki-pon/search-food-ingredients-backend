package com.sfi.backend.presentation.form.food

import com.sfi.backend.domain.model.Food

data class FoodResponse(
    val id: String,
    val userId: String,
    val name: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrate: Int,
    val perGrams: Int
) {
    constructor(food: Food): this(
        id = food.id,
        userId = food.userId,
        name = food.name,
        calories = food.calories,
        protein = food.protein,
        fat = food.fat,
        carbohydrate = food.carbohydrate,
        perGrams = food.perGrams
    )
}
