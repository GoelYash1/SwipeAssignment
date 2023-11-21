package com.example.swipeassignment.data.repo

import com.example.swipeassignment.api.ProductApi
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.util.Resource

class ProductRepositoryImpl(
    private val api: ProductApi
) : ProductRepository{
    override suspend fun getProducts(): Resource<List<Product>> {
        return try {
            val response = api.getProducts()
            Resource.Success(response)
        }catch (e:Exception){
            Resource.Error(e)
        }
    }
}