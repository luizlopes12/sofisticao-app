    package com.example.app.navigation

    import HomeScreen
    import ShopScreen
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

        NavHost(navController = navController, startDestination = startDestination) {
            composable("product/{id}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("id") ?: ""
                ProductScreen(navController, productId)
            }
            composable("shop") { ShopScreen(navController) }
            composable("home") { HomeScreen(navController) }
        }
    }