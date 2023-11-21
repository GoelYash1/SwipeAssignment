package com.example.swipeassignment.screens.productListScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.swipeassignment.util.Resource
import com.example.swipeassignment.viewmodels.ProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(productListViewModel: ProductListViewModel) {
    var searchText by remember { mutableStateOf("") }
    val resource by productListViewModel.products.collectAsState(Resource.Loading())

    LaunchedEffect(Unit) {
        productListViewModel.getProducts()
    }


    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it},
            placeholder = {
                Text("Search products")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                )
            }
        )
        when(resource){
            is Resource.Loading->{
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            is Resource.Success->{

                val filteredProducts = resource.data?.filter {
                    it.product_type.contains(searchText.trim(), ignoreCase = true) ||
                            it.product_name.contains(searchText.trim(), ignoreCase = true)
                }?: emptyList()
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(filteredProducts) { product ->
                        ProductListItem(product)
                    }
                }
            }
            is Resource.Error->{
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Error: ${resource.error?.message}")
                }
            }
        }

    }
}


