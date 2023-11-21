package com.example.swipeassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.swipeassignment.viewmodels.ProductAddViewModel
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
                            Row (
                                Modifier
                                    .fillMaxWidth()
                                    .padding(end = 10.dp),
                                Arrangement.SpaceBetween,
                                Alignment.CenterVertically
                            ){
                                Text(
                                    text = "Product",
                                    fontSize = 28.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "",
                                    modifier = Modifier.clickable (
                                        onClick = {
                                            navController.navigate("addProductScreen")
                                        }
                                    )

                                )
                            }

                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.Cyan.copy(alpha = 0.5f)
                        )
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
        val productAddViewModel = getViewModel<ProductAddViewModel>()
            AddProductScreen(navController,productAddViewModel)
        }
        composable("productListScreen") {
            val productListViewModel = getViewModel<ProductListViewModel>()
            ProductListScreen(productListViewModel = productListViewModel)
        }
    }
}