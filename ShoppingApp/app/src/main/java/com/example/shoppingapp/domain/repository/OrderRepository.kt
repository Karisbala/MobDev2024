package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.Order
import com.example.shoppingapp.domain.model.CartItem

interface OrderRepository {
    suspend fun placeOrder(userId: String, items: List<CartItem>): Order
    suspend fun getOrders(userId: String): List<Order>
    suspend fun getOrderById(orderId: String): Order?
}