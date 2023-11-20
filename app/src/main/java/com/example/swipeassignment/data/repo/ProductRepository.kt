package com.example.swipeassignment.data.repo

import com.example.swipeassignment.data.models.ProductModel

interface ProductRepository {
    suspend fun getProducts(): List<ProductModel>
}