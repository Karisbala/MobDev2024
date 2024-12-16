package com.example.shoppingapp.ui.feature.products

import com.example.shoppingapp.domain.model.Category
import com.example.shoppingapp.domain.model.Product

data class ProductsState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null,
    val categories: List<Category> = emptyList(),
    val selectedCategories: Set<String> = emptySet(),
    val cartAddMessage: String? = null
)