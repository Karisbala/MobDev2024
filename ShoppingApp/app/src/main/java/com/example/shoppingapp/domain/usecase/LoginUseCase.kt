package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.User
import com.example.shoppingapp.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): User {
        return userRepository.login(email, password)
    }
}
