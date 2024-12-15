package com.example.shoppingapp.domain.model

data class Order(
    val orderId: String,
    val userId: String,
    val orderDate: Long,
    val totalAmount: Double,
    val status: String,
    val items: List<OrderItem>
)