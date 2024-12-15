package com.example.shoppingapp.domain.model

data class Product(
    val productId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)