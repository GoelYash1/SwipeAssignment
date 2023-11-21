package com.example.swipeassignment.data.models

import okhttp3.MultipartBody

data class Product(
    val image: String?,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)

data class PostProduct(
    val productName: String,
    val productType: String,
    val price: String,
    val tax: String,
)