package com.example.swipeassignment.data.repo

import com.example.swipeassignment.data.models.PostProduct
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.util.Resource

interface ProductRepository {
    suspend fun getProducts(): Resource<List<Product>>
    suspend fun addProduct(postProduct: PostProduct): ProductApiResponse
}