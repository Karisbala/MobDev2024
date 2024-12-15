package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.CartRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(userId: String, productId: String) {
        cartRepository.removeCartItem(userId, productId)
    }
}