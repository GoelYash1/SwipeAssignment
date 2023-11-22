package com.example.swipeassignment.data.repo

import com.example.swipeassignment.api.ProductApi
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.util.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

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

    override suspend fun addProduct(product: Product): ProductApiResponse {
        val productType = product.product_type.toRequestBody(MultipartBody.FORM)
        val productName = product.product_name.toRequestBody(MultipartBody.FORM)
        val price = product.price.toString().toRequestBody(MultipartBody.FORM)
        val tax = product.tax.toString().toRequestBody(MultipartBody.FORM)
        return api.addProduct(productName = productName,productType = productType,price = price,tax=tax)
    }
}