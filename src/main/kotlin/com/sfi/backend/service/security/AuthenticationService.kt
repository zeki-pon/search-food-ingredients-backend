package com.sfi.backend.service.security

import com.sfi.backend.domain.model.User
import com.sfi.backend.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
) {
    fun findUser(email: String): User? {
        return userRepository.findByEmail(email)
    }
}