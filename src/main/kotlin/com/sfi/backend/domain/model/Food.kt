package com.sfi.backend.domain.model

data class Food(
    val id: Long,
    val userId: String,
    val name: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrate: Int,
    val perGrams: Int
)
