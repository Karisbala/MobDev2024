package com.example.shoppingapp.ui.feature.orders

sealed class OrdersIntent {
    data object LoadOrders : OrdersIntent()
}