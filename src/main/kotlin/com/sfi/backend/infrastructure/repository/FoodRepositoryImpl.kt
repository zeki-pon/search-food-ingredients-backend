package com.sfi.backend.infrastructure.repository

import com.sfi.backend.domain.model.Food
import com.sfi.backend.domain.repository.FoodRepository
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class FoodRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
): FoodRepository {

    val createSql = "INSERT INTO food_ingredients_app.foods " +
            "(id, user_id, name, calories, protein, fat, carbohydrate, per_grams, created_at, updated_at) " +
            "VALUES (:id, :userId, :name, :calories, :protein, :fat, :carbohydrate, :perGrams, :createdAt, :updatedAt)"

    val findByIdSql = "SELECT id, user_id, name, calories, protein, fat, carbohydrate, per_grams FROM food_ingredients_app.foods " +
            "WHERE id = :id"

    val findByUserIdSql = "SELECT id, user_id, name, calories, protein, fat, carbohydrate, per_grams FROM food_ingredients_app.foods " +
            "WHERE user_id = :userId"

    val updateSql = "UPDATE food_ingredients_app.foods SET " +
            "(name, calories, protein, fat, carbohydrate, per_grams, updated_at) = " +
            "(:name, :calories, :protein, :fat, :carbohydrate, :perGrams, :updatedAt) " +
            "WHERE id = :id"

    val deleteSql = "DELETE FROM food_ingredients_app.foods WHERE id = :id"


    override fun create(food: Food) {
        val params = mapOf(
            "id" to food.id,
            "userId" to food.userId,
            "name" to food.name,
            "calories" to food.calories,
            "protein" to food.protein,
            "fat" to food.fat,
            "carbohydrate" to food.carbohydrate,
            "perGrams" to food.perGrams,
            "createdAt" to OffsetDateTime.now(),
            "updatedAt" to OffsetDateTime.now()
        )
        jdbcTemplate.update(createSql, params)
    }

    override fun findById(id: Long): Food? {
        val params = mapOf(
            "id" to id
        )
        val rows = jdbcTemplate.query(findByIdSql, params, DataClassRowMapper(Food::class.java))
        return if(rows.isEmpty()) null else rows.first()
    }

    override fun findByUserId(userId: String): List<Food> {
        val params = mapOf(
            "userId" to userId
        )
        return jdbcTemplate.query(findByUserIdSql, params, DataClassRowMapper(Food::class.java))
    }

    override fun update(food: Food) {
        val params = mapOf(
            "id" to food.id,
            "name" to food.name,
            "calories" to food.calories,
            "protein" to food.protein,
            "fat" to food.fat,
            "carbohydrate" to food.carbohydrate,
            "perGrams" to food.perGrams,
            "updatedAt" to OffsetDateTime.now()
        )
        jdbcTemplate.update(updateSql, params)
    }

    override fun delete(id: Long) {
        val params = mapOf(
            "id" to id
        )
        jdbcTemplate.update(deleteSql, params)
    }
}