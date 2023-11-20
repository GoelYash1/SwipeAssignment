package com.example.swipeassignment.data.repo

import com.example.swipeassignment.data.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}