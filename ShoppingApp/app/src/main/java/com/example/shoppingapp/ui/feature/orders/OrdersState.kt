package com.example.shoppingapp.ui.feature.orders

import com.example.shoppingapp.domain.model.Order

data class OrdersState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val error: String? = null
)