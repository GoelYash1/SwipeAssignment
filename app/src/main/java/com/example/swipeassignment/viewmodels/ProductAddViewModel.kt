package com.example.swipeassignment.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.data.models.PostProduct
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.data.repo.ProductRepository
import com.example.swipeassignment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductAddViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _addProductResult: MutableLiveData<Resource<ProductApiResponse>> = MutableLiveData()
    val addProductResult :LiveData<Resource<ProductApiResponse>> = _addProductResult
    fun addProduct(postProduct: PostProduct) {
        _addProductResult.value = Resource.Loading()

        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.addProduct(postProduct)
                }

                _addProductResult.value = Resource.Success(result)
            } catch (e: Exception) {
                _addProductResult.value = Resource.Error(e)
                Log.e("AddProduct", "Error adding product", e)
            }
        }
    }
}
