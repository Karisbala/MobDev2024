package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.User

interface UserRepository {
    suspend fun register(username: String, email: String, password: String): User
    suspend fun login(email: String, password: String): User
    suspend fun getUserById(userId: String): User?
}