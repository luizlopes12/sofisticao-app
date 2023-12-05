package com.example.app.navigation

import HomeScreen
import androidx.navigation.compose.composable
import com.example.app.R
import com.example.app.presentations.Product.Product
import com.example.app.presentations.Product.ProductScreen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AppNavHost(navController: NavHostController, startDestination: String, context: Context){
    val product = Product(
        imageRes = R.drawable.product__image,
        currentPrice = 49.99,
        originalPrice = 99.99,
        name = "Roupinha - Sofi Wear",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris cursus dui eget nisl aliquam, sed tincidunt libero tristique. Sed et."
    )
    NavHost(navController = navController, startDestination = startDestination) {
        composable("product") { ProductScreen(navController, product) }
        composable("home") { HomeScreen(navController) }
    }
}