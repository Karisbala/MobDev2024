package com.example.shoppingapp.data.remote.dto

data class UserDto(
    val userId: String,
    val username: String,
    val email: String,
    val passwordHash: String
)