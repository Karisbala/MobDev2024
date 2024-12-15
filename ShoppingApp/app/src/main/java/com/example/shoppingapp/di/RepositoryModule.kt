package com.example.shoppingapp.di

import com.example.shoppingapp.data.repository.CartRepositoryImpl
import com.example.shoppingapp.data.repository.OrderRepositoryImpl
import com.example.shoppingapp.data.repository.ProductRepositoryImpl
import com.example.shoppingapp.data.repository.UserRepositoryImpl
import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.repository.OrderRepository
import com.example.shoppingapp.domain.repository.ProductRepository
import com.example.shoppingapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}