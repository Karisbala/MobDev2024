package com.example.shoppingapp.ui.feature.auth.login

sealed class LoginIntent {
    data class Login(val email: String, val password: String) : LoginIntent()
    data object ResetError : LoginIntent()
}