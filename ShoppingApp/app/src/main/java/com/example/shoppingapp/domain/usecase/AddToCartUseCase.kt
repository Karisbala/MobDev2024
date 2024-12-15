package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(userId: String, productId: String, quantity: Int) {
        cartRepository.addCartItem(userId, productId, quantity)
    }
}