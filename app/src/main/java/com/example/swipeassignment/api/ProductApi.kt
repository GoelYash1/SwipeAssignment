package com.example.swipeassignment.api

import com.example.swipeassignment.data.models.PostProduct
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.util.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {
    @GET("get")
    suspend fun getProducts(): List<Product>

    @POST("add")
    fun addProduct(@Body postProduct: PostProduct):ProductApiResponse
}