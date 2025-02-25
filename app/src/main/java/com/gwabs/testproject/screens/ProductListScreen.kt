package com.gwabs.testproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gwabs.testproject.data.response.Product
import com.gwabs.testproject.viewModel.ProductsViewModel


@Composable
fun ProductListScreen(
    viewModel: ProductsViewModel,
) {

    var products by remember { mutableStateOf<List<Product>>(emptyList()) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoadinng by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isLoadinng = true
        viewModel.getProduct(onError = {
            isLoadinng = false
            errorMessage = it
        }, onSuccess = {
            isLoadinng = false
            products = it
        })
    }


    Column(
        modifier = Modifier.fillMaxSize(),
    ) {


        if (isLoadinng) {

            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        } else {
            LazyColumn(Modifier.padding(10.dp)) {
                items(products ?: emptyList()) { product ->
                    ProductCard(product = product, onClick = {  })
                }
            }
        }


    }


}


@Composable
private fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(10.dp),

        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(300.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = product.images[0],
                contentDescription = product.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = product.title.split(" ").take(3).joinToString(" "),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "₦${product.price}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}