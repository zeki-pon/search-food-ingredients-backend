package com.sfi.backend.domain.model

import com.sfi.backend.presentation.form.food.RegisterFoodRequest
import com.sfi.backend.presentation.form.food.UpdateFoodRequest
import com.sfi.backend.util.IdentifierUtil

data class Food(
    val id: String,
    val userId: String,
    val name: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrate: Int,
    val perGrams: Int
) {
    constructor(from: RegisterFoodRequest, userId: String): this(
        id = IdentifierUtil.generateId("food_", 21),
        userId = userId,
        name = from.name,
        calories = from.calories,
        protein = from.protein,
        fat = from.fat,
        carbohydrate = from.carbohydrate,
        perGrams = from.perGrams
    )

    constructor(from: UpdateFoodRequest, userId: String): this(
        id = from.foodId,
        userId = userId,
        name = from.name,
        calories = from.calories,
        protein = from.protein,
        fat = from.fat,
        carbohydrate = from.carbohydrate,
        perGrams = from.perGrams
    )
}
