package com.example.shoppingapp.domain.model

data class OrderItem(
    val productId: String,
    val quantity: Int,
    val price: Double
)