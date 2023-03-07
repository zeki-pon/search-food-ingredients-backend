package com.sfi.backend.domain.repository

import com.sfi.backend.domain.model.User

interface UserRepository {
    fun create(user: User)
    fun findByEmail(email: String): User?
    fun delete(id: String)
}