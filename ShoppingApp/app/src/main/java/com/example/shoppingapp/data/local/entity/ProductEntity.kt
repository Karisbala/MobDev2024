package com.example.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class ProductEntity(
    @PrimaryKey val productId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)