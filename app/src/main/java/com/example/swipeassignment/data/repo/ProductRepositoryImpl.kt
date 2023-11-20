package com.example.swipeassignment.data.repo

import com.example.swipeassignment.api.ProductApi
import com.example.swipeassignment.data.models.Product

class ProductRepositoryImpl(
    private val api: ProductApi
) : ProductRepository{
    override suspend fun getProducts(): List<Product> {
        return try {
            api.getProducts()
        }catch (e:Exception){
            emptyList<Product>()
        }
    }
}