package com.example.shoppingapp.data.remote.dto

data class LoginResponse(
    val userId: String,
    val username: String,
    val email: String,
    val passwordHash: String
)