package com.example.swipeassignment.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.data.models.ProductModel
import com.example.swipeassignment.data.repo.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductRepository
): ViewModel() {
    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> get() = _products

    fun getProducts() {
        viewModelScope.launch {
            try {
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                // Handle error
                Log.e("ProductListViewModel", "Error fetching products", e)
            }
        }
    }
}