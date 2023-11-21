package com.example.swipeassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.repo.ProductRepository
import com.example.swipeassignment.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val products: StateFlow<Resource<List<Product>>> get() = _products

    fun getProducts() {
        viewModelScope.launch {
            _products.value = repository.getProducts()
        }
    }
}
