package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.User
import com.example.shoppingapp.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, email: String, password: String): User {
        return userRepository.register(username, email, password)
    }
}