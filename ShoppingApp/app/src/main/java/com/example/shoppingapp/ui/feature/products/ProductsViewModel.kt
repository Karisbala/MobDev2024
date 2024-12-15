package com.example.shoppingapp.ui.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.AddToCartUseCase
import com.example.shoppingapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state: StateFlow<ProductsState> get() = _state

    fun handleIntent(intent: ProductsIntent) {
        when (intent) {
            is ProductsIntent.LoadProducts -> loadProducts()
            is ProductsIntent.AddToCart -> addToCart(intent.productId)
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val products = getProductsUseCase()
                _state.value = ProductsState(isLoading = false, products = products)
            } catch (e: Exception) {
                _state.value = ProductsState(isLoading = false, error = e.message)
            }
        }
    }

    private fun addToCart(productId: String) {
        viewModelScope.launch {
            try {
                addToCartUseCase("currentUserId", productId, 1) // In real scenario, fetch currentUserId from session
            } catch (e: Exception) {
                // Possibly show a toast or some feedback
            }
        }
    }
}