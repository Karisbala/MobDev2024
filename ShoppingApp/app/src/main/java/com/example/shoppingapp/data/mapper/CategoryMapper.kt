package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.domain.model.Category

fun String.toCategory(): Category {
    return Category(
        categoryId = this,
        categoryName = this
    )
}