package com.sfi.backend.service.user

import com.sfi.backend.domain.model.User
import com.sfi.backend.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(user: User): User {
        userRepository.create(user)

        return userRepository.findByEmail(user.email)!!
    }

    fun getDetail(email: String): User {
        return userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("This user data is not found")
    }
}