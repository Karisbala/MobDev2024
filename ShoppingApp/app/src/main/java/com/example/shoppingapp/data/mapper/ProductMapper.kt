package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.remote.dto.ProductDto
import com.example.shoppingapp.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        productId = this.id.toString(),
        name = this.title,
        description = this.description,
        price = this.price,
        imageUrl = this.image,
        category = this.category
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        productId = this.productId,
        name = this.name,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl,
        category = this.category
    )
}

fun ProductEntity.toDomain(): Product {
    return Product(
        productId = this.productId,
        name = this.name,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl,
        category = this.category
    )
}