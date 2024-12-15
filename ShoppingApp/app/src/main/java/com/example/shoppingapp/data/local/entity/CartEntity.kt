package com.example.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingCart")
data class CartEntity(
    @PrimaryKey val cartId: String,
    val userId: String,
    val createdAt: Long
)