package com.example.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Orders")
data class OrderEntity(
    @PrimaryKey val orderId: String,
    val userId: String,
    val orderDate: Long,
    val totalAmount: Double,
    val status: String
)