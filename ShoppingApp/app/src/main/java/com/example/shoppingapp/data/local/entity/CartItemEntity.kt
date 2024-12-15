package com.example.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CartItems")
data class CartItemEntity(
    @PrimaryKey val cartItemId: String,
    val cartId: String,
    val productId: String,
    val quantity: Int
)