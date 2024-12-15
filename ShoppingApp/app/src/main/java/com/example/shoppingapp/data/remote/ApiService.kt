package com.example.shoppingapp.data.remote

import com.example.shoppingapp.data.remote.dto.LoginRequest
import com.example.shoppingapp.data.remote.dto.LoginResponse
import com.example.shoppingapp.data.remote.dto.ProductDto
import com.example.shoppingapp.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: String): ProductDto?

    @GET("products/categories")
    suspend fun getCategories(): List<String>
}