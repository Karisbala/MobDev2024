package com.example.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderItems")
data class OrderItemEntity(
    @PrimaryKey val orderItemId: String,
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val price: Double
)