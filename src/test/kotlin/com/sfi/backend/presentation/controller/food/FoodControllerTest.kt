package com.sfi.backend.presentation.controller.food

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.sfi.backend.domain.model.Food
import com.sfi.backend.presentation.form.food.FoodListResponse
import com.sfi.backend.presentation.form.food.FoodResponse
import com.sfi.backend.presentation.form.food.RegisterFoodRequest
import com.sfi.backend.presentation.form.food.UpdateFoodRequest
import com.sfi.backend.service.food.FoodService
import com.sfi.backend.service.security.SfiUserDetails
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.nio.charset.StandardCharsets

internal class FoodControllerTest {
    private val foodService = mock<FoodService>()
    private val foodController = FoodController(foodService)
    private val auth = mock<Authentication>()

    @BeforeEach
    fun initSecurityContext() {
        whenever(auth.principal).thenReturn(SfiUserDetails(
            id = "user_12345",
            email = "test@test.co.jp",
            pass = "password"
        ))
        SecurityContextHolder.getContext().authentication = auth
    }

    @Test
    fun register_when_success() {
        // mock
        val food = createFood()
        whenever(foodService.createFood(any())).thenReturn(food)

        // expected
        val expectedResponse = FoodResponse(createFood())
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)

        // given
        val givenRequest = RegisterFoodRequest(
            name = "name",
            calories = 100,
            protein = 100,
            fat = 100,
            carbohydrate = 100,
            perGrams = 100
        )
        val given = ObjectMapper().registerKotlinModule().writeValueAsString(givenRequest)

        val resultResponse =
            MockMvcBuilders.standaloneSetup(foodController).build()
                .perform(
                    MockMvcRequestBuilders
                        .post("/food/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(given)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getList_when_success() {
        // mock
        val food = createFood()
        val foodList = listOf(createFood())
        whenever(foodService.getList(any())).thenReturn(foodList)

        // expected
        val expectedResponse = FoodListResponse(
            listOf(FoodResponse(createFood()))
        )
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)

        val resultResponse =
            MockMvcBuilders.standaloneSetup(foodController).build()
                .perform(MockMvcRequestBuilders.get("/food/list"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getDetail_when_success() {
        // mock
        val food = createFood()
        whenever(foodService.getDetail(any(), any())).thenReturn(food)

        // expected
        val expectedResponse = FoodResponse(createFood())
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)

        val resultResponse =
            MockMvcBuilders.standaloneSetup(foodController).build()
                .perform(MockMvcRequestBuilders.get("/food/detail/food_12345"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }

    @Test
    fun update_when_success() {
        // mock
        val food = createFood()
        whenever(foodService.updateFood(any())).thenReturn(food)

        // expected
        val expectedResponse = FoodResponse(createFood())
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)

        // given
        val givenRequest = UpdateFoodRequest(
            foodId = "food_12345",
            name = "name",
            calories = 100,
            protein = 100,
            fat = 100,
            carbohydrate = 100,
            perGrams = 100
        )
        val given = ObjectMapper().registerKotlinModule().writeValueAsString(givenRequest)

        val resultResponse =
            MockMvcBuilders.standaloneSetup(foodController).build()
                .perform(
                    MockMvcRequestBuilders
                        .put("/food/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(given)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }

    @Test
    fun delete_when_success() {
        // mock
        val food = createFood()
        whenever(foodService.deleteFood(any(), any())).thenReturn(food)

        // expected
        val expectedResponse = FoodResponse(createFood())
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)

        val resultResponse =
            MockMvcBuilders.standaloneSetup(foodController).build()
                .perform(MockMvcRequestBuilders.delete("/food/delete/food_12345"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }

    private fun createFood(): Food {
        return Food(
            id = "food_12345",
            userId = "user_12345",
            name = "name",
            calories = 100,
            protein = 100,
            fat = 100,
            carbohydrate = 100,
            perGrams = 100
        )
    }
}