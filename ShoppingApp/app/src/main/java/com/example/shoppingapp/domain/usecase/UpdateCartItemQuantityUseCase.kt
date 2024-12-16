package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.CartRepository
import javax.inject.Inject

class UpdateCartItemQuantityUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(userId: String, productId: String, newQuantity: Int) {
        cartRepository.updateCartItemQuantity(userId, productId, newQuantity)
    }
}