package com.example.swipeassignment.screens.productListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.swipeassignment.data.models.Product
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@Composable
fun ProductListItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = if (product.image.isNullOrBlank()) {
                    "https://vx-erp-product-images.s3.ap-south-1.amazonaws.com/9_1619873597_WhatsApp_Image_2021-04-30_at_19.43.23.jpeg"
                } else {
                    product.image
                },
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.product_name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
                val roundedPrice = BigDecimal(product.price.toString())
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .toDouble()
                Text(
                    text = "Rs. $roundedPrice",
                    fontSize = 12.sp,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "(${product.product_type})",
                    fontSize = 14.sp,
                )
                Text(
                    text = "${product.tax}",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}