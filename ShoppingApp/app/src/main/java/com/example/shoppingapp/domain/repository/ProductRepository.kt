package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.Category
import com.example.shoppingapp.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(productId: String): Product?
    suspend fun getCategories(): List<Category>
}