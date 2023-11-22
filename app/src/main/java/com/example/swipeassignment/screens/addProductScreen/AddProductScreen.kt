package com.example.swipeassignment.screens.addProductScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swipeassignment.data.models.Product
import com.example.swipeassignment.data.models.ProductApiResponse
import com.example.swipeassignment.util.Resource
import com.example.swipeassignment.viewmodels.ProductAddViewModel

@Composable
fun AddProductScreen(navController: NavHostController, productAddViewModel: ProductAddViewModel) {
    var productType by remember { mutableStateOf("A") }
    var productName by remember { mutableStateOf("Test") }
    var tax by remember { mutableStateOf("20.00") }
    var price by remember { mutableStateOf("150.05") }

    val productAdded by productAddViewModel.addProductResult.observeAsState()
    var toastText by remember { mutableStateOf("") }

    var showToast by remember { mutableStateOf(false) }
    var successfulAdd by remember { mutableStateOf(false) }

    when (val result = productAdded) {
        is Resource.Success -> {
            val message = result.data?.message
            message?.let {
                toastText = it
            }
            successfulAdd = true
        }
        is Resource.Loading -> LoadingContent()
        is Resource.Error -> {
            val error = (result as Resource.Error<ProductApiResponse>).error
            error?.let {
                Log.d("Error", it.message ?: "")
                toastText = if (productName.isNotEmpty() && productType.isNotEmpty() && tax.isNotEmpty() && price.isNotEmpty()) {
                    it.message ?: ""
                } else {
                    "No Field Can Be Empty"
                }
            }
        }

        else -> {}
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Product Details",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // Input fields
        ProductTextField(value = productType, onValueChange = { productType = it }, label = "Product Type")
        Spacer(modifier = Modifier.padding(8.dp))

        ProductTextField(value = productName, onValueChange = { productName = it }, label = "Product Name")
        Spacer(modifier = Modifier.padding(8.dp))

        ProductTextField(
            value = tax,
            onValueChange = {
                if (it.isNumericWithMaxDecimalPlaces(6, 100.toDouble())) {
                    tax = it
                }
            },
            label = "Tax",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.padding(8.dp))

        ProductTextField(
            value = price,
            onValueChange = {
                if (it.isNumericWithMaxDecimalPlaces(6, 1000000.toDouble())) {
                    price = it
                }
            },
            label = "Price",
            keyboardType = KeyboardType.Number
        )

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ProductButton(
                onClick = {
                    if (productName.isNotEmpty() && productType.isNotEmpty() && tax.isNotEmpty() && price.isNotEmpty()) {
                        val dummyProduct = Product(
                            product_type = productType,
                            product_name = productName,
                            price = price.toDoubleOrNull() ?: 0.0,
                            tax = tax.toDoubleOrNull() ?: 0.0
                        )
                        productAddViewModel.addProduct(dummyProduct)
                    } else {
                        toastText = "No Field Can be Empty"
                        showToast = true
                    }
                },
                buttonText = "Add Product"
            )

        }
    }

    if (showToast) {
        Toast.makeText(LocalContext.current, toastText, Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(successfulAdd) {
        if (successfulAdd) {
            navController.navigate("productListScreen") {
                popUpTo("addProductScreen") {
                    inclusive = true
                }
            }
            successfulAdd = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Composable
private fun ProductButton(onClick: () -> Unit, buttonText: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Cyan.copy(0.5f),
            contentColor = Color.Black
        )
    ) {
        Text(buttonText)
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

fun String.isNumericWithMaxDecimalPlaces(maxDecimalPlaces: Int, maxValue: Double): Boolean {
    if (this.isEmpty()) {
        return true
    }

    return try {
        val numericValue = this.toDouble()
        val decimalPlaces = this.substringAfter('.', "").length
        numericValue <= maxValue && decimalPlaces <= maxDecimalPlaces
    } catch (e: NumberFormatException) {
        false
    }
}

