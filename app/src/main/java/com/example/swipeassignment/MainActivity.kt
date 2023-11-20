package com.example.swipeassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swipeassignment.screens.addProductScreen.AddProductScreen
import com.example.swipeassignment.screens.productListScreen.ProductListScreen
import com.example.swipeassignment.viewmodels.ProductListViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Product",
                                fontSize = 28.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    )
                }
            ) {
                Box(modifier = Modifier.padding(it)){
                    Navigation(navController)
                }
            }
        }
    }
}
@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "productListScreen") {
        composable("addProductScreen") {
            AddProductScreen()
        }
        composable("productListScreen") {
            val productListViewModel = getViewModel<ProductListViewModel>()
            ProductListScreen(productListViewModel = productListViewModel)
        }
    }
}