package com.example.swipeassignment.data.models

data class Product(
    val image: String?,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)

data class PostProduct(
    val price: String,
    val product_name: String,
    val product_type: String,
    val tax: String
)