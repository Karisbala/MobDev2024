package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}