package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.dao.ProductDao
import com.example.shoppingapp.data.mapper.toDomain
import com.example.shoppingapp.data.mapper.toEntity
import com.example.shoppingapp.data.mapper.toCategory
import com.example.shoppingapp.data.remote.ApiService
import com.example.shoppingapp.domain.model.Category
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        val productDtos = apiService.getProducts()
        val products = productDtos.map { it.toDomain() }
        productDao.insertProducts(products.map { it.toEntity() })
        products
    }

    override suspend fun getProductById(productId: String): Product? = withContext(Dispatchers.IO) {
        val localProduct = productDao.getProductById(productId)
        if (localProduct != null) {
            return@withContext localProduct.toDomain()
        }

        val dto = apiService.getProductById(productId) ?: return@withContext null
        val product = dto.toDomain()
        productDao.insertProducts(listOf(product.toEntity()))
        product
    }

    override suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        val categories = apiService.getCategories()
        categories.map { it.toCategory() }
    }
}