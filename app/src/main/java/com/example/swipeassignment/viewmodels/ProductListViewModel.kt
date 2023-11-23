package com.example.swipeassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.repo.ProductRepository
import com.example.swipeassignment.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<Resource<List<Product>>>(Resource.Loading())
    val products: LiveData<Resource<List<Product>>> get() = _products
    private var allProducts: List<Product> = emptyList()

    fun getProducts() {
        viewModelScope.launch {
            try {
                allProducts = repository.getProducts().data ?: emptyList()
                updateFilteredProducts()
            } catch (e: Exception) {
                _products.value = Resource.Error(e)
            }
        }
    }

    fun setSearchQuery(query: String) {
        updateFilteredProducts(query)
    }

    private fun updateFilteredProducts(query: String?=null) {
        val filteredProducts = if(query.isNullOrBlank()){
            allProducts
        } else{
            allProducts.filter {
                it.product_type.contains(query.trim(),ignoreCase = true) ||
                        it.product_name.contains(query.trim(), ignoreCase = true)
            }
        }

        _products.value = Resource.Success(filteredProducts)
    }
}

