package com.example.swipeassignment.data.models

data class ProductApiResponse(
    val message: String,
    val product_details: PostProduct,
    val product_id: Int,
    val success: Boolean
)