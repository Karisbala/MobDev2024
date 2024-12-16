package com.example.shoppingapp.ui.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.repository.ProductRepository
import com.example.shoppingapp.domain.usecase.AddToCartUseCase
import com.example.shoppingapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state: StateFlow<ProductsState> get() = _state

    fun handleIntent(intent: ProductsIntent) {
        when (intent) {
            ProductsIntent.LoadProducts -> loadData()
            is ProductsIntent.AddToCart -> addToCart(intent.productId)
            is ProductsIntent.ToggleCategory -> toggleCategory(intent.categoryId)
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val products = getProductsUseCase()
                val categories = productRepository.getCategories()
                _state.value = _state.value.copy(
                    isLoading = false,
                    products = products,
                    categories = categories
                )
            } catch (e: Exception) {
                _state.value = ProductsState(isLoading = false, error = e.message)
            }
        }
    }

    private fun addToCart(productId: String) {
        viewModelScope.launch {
            try {
                addToCartUseCase("currentUserId", productId, 1)
                _state.value = _state.value.copy(cartAddMessage = "Item added to cart")
            } catch (e: Exception) {
                // handle error if needed
            }
        }
    }

    fun resetCartMessage() {
        _state.value = _state.value.copy(cartAddMessage = null)
    }

    private fun toggleCategory(categoryId: String) {
        val currentSelected = _state.value.selectedCategories.toMutableSet()
        if (currentSelected.contains(categoryId)) {
            currentSelected.remove(categoryId)
        } else {
            currentSelected.add(categoryId)
        }
        _state.value = _state.value.copy(selectedCategories = currentSelected)
    }
}