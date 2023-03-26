package com.sfi.backend.presentation.form.food

data class RegisterFoodRequest(
    val name: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrate: Int,
    val perGrams: Int
)
