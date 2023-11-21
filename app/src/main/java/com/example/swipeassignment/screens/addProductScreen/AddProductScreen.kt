package com.example.swipeassignment.screens.addProductScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.swipeassignment.data.models.PostProduct
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.util.Resource
import com.example.swipeassignment.viewmodels.ProductAddViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavHostController, productAddViewModel: ProductAddViewModel) {
    var productType by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var tax by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    val productAdded by productAddViewModel.addProductResult.observeAsState()
    var toastText by remember { mutableStateOf("") }
    when (productAdded) {
        is Resource.Success -> {
            val result = (productAdded as Resource.Success<ProductApiResponse>).data
            if (result != null) {
                Log.d("Result is Here", result.message)
                toastText = result.message
                Toast.makeText(LocalContext.current, toastText, Toast.LENGTH_SHORT).show()
            }
        }
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Resource.Error -> {
            val error = (productAdded as Resource.Error<ProductApiResponse>).error
            if (error != null) {
                error.message?.let { Log.d("Error", it) }
                toastText = if(productName.isNotBlank() && productType.isNotBlank() && price.isNotBlank() && tax.isNotBlank()){
                    error.message.toString()
                }else{
                    "No Field Can Be Empty"
                }
                Toast.makeText(LocalContext.current, toastText, Toast.LENGTH_SHORT).show()
            }
        }
        else -> {
        }
    }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .pointerInput(Unit){
                detectTapGestures(onTap =  {
                    focusManager.clearFocus()
                })
            }
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = productType,
            onValueChange = { productType = it },
            label = { Text("Product Type") }
        )

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") }
        )

        OutlinedTextField(
            value = tax,
            onValueChange = { tax = it },
            label = { Text("Tax") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    val dummyProduct = PostProduct(
                        productType = productType,
                        productName = productName,
                        price = price,
                        tax = tax
                    )

                    productAddViewModel.addProduct(dummyProduct)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Add Product")
            }
        }
    }
}

