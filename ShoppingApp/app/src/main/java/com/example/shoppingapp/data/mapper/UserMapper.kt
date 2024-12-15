package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.local.entity.UserEntity
import com.example.shoppingapp.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        userId = this.userId,
        username = this.username,
        email = this.email
    )
}

fun User.toEntity(passwordHash: String): UserEntity {
    return UserEntity(
        userId = this.userId,
        username = this.username,
        email = this.email,
        passwordHash = passwordHash
    )
}