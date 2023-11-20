package com.example.swipeassignment.api

import com.example.swipeassignment.data.models.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("get")
    suspend fun getProducts(): List<Product>
}