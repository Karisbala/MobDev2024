package com.example.shoppingapp.ui.feature.products

import com.example.shoppingapp.domain.model.Product

data class ProductsState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)