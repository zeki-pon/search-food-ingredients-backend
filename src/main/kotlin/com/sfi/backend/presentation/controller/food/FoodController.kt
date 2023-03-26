package com.sfi.backend.presentation.controller.food

import com.sfi.backend.domain.model.Food
import com.sfi.backend.presentation.form.food.FoodListResponse
import com.sfi.backend.presentation.form.food.FoodResponse
import com.sfi.backend.presentation.form.food.RegisterFoodRequest
import com.sfi.backend.presentation.form.food.UpdateFoodRequest
import com.sfi.backend.service.food.FoodService
import com.sfi.backend.service.security.SfiUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("food")
class FoodController(
    private val foodService: FoodService
) {
    /*
      TODO: バリデーション
     */

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterFoodRequest): FoodResponse {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        return FoodResponse(foodService.createFood(Food(request, user.id)))
    }

    @GetMapping("/list")
    fun getList(): FoodListResponse {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        return FoodListResponse(foods = foodService.getList(user.id).map{ FoodResponse(it)})
    }

    @GetMapping("/detail/{food_id}")
    fun getDetail(@PathVariable("food_id") foodId: String): FoodResponse {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        return FoodResponse(foodService.getDetail(foodId, user.id))
    }

    @PutMapping("/update")
    fun update(@RequestBody request: UpdateFoodRequest): FoodResponse {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        return FoodResponse(foodService.updateFood(Food(request, user.id)))
    }

    @DeleteMapping("/delete/{food_id}")
    fun delete(@PathVariable("food_id") foodId: String): FoodResponse {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        return FoodResponse(foodService.deleteFood(foodId, user.id))
    }

}