package com.sfi.backend.infrastructure.repository

import com.sfi.backend.domain.model.User
import com.sfi.backend.domain.repository.UserRepository
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime


@Repository
class UserRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
): UserRepository {

    val createSql = "INSERT INTO food_ingredients_app.users " +
            "(id, name, email, password, created_at, updated_at) " +
            "VALUES (:id, :name, :email, :password, :createdAt, :updatedAt)"

    val findByEmailSql = "SELECT id, name, email, password FROM food_ingredients_app.users WHERE email = :email"

    val deleteSql = "DELETE FROM food_ingredients_app.users WHERE id = :id"

    override fun create(user: User) {
            val params = mapOf(
                "id" to user.id,
                "name" to user.name,
                "email" to user.email,
                "password" to user.password,
                "createdAt" to OffsetDateTime.now(),
                "updatedAt" to OffsetDateTime.now()
            )
            jdbcTemplate.update(createSql, params)
    }

    override fun findByEmail(email: String): User? {
        val params = mapOf(
            "email" to email
        )
        val rows = jdbcTemplate.query(findByEmailSql, params, DataClassRowMapper(User::class.java))
        return if(rows.isEmpty()) null else rows.first()
    }

    override fun delete(id: String) {
        val params = mapOf(
            "id" to id
        )
        jdbcTemplate.update(deleteSql, params)
    }
}