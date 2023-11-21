package com.example.swipeassignment.data.repo

import com.example.swipeassignment.api.ProductApi
import com.example.swipeassignment.data.models.PostProduct
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.util.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody

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

    override suspend fun addProduct(postProduct: PostProduct): ProductApiResponse {
        val productType = postProduct.productType.toRequestBody(MultipartBody.FORM)
        val productName = postProduct.productName.toRequestBody(MultipartBody.FORM)
        val price = postProduct.price.toRequestBody(MultipartBody.FORM)
        val tax = postProduct.tax.toRequestBody(MultipartBody.FORM)
        return api.addProduct(productName = productName,productType = productType,price = price,tax=tax)
    }
}