package com.example.swipeassignment.data.repo

import com.example.swipeassignment.api.ProductApiService
import com.example.swipeassignment.data.models.ProductModel

class ProductRepositoryImpl(
    private val api: ProductApiService
) : ProductRepository{
    override suspend fun getProducts(): List<ProductModel> {
        return try {
            api.getProducts()
        }catch (e:Error){
            emptyList<ProductModel>()
        }
    }
}