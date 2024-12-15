package com.example.shoppingapp.ui.feature.auth.register

sealed class RegisterIntent {
    data class Register(val username: String, val email: String, val password: String) : RegisterIntent()
    data object ResetError : RegisterIntent()
}