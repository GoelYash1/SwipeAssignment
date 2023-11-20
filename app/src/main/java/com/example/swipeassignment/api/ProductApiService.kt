package com.example.swipeassignment.api

import com.example.swipeassignment.data.models.ProductModel
import retrofit2.http.GET

interface ProductApiService {
    @GET("get")
    suspend fun getProducts(): List<ProductModel>
}