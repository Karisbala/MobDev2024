package com.example.shoppingapp.ui.feature.auth.register

data class RegisterState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)