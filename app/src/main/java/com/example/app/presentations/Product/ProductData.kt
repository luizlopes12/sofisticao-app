package com.example.app.presentations.Product

import android.icu.math.BigDecimal

/*
data class Product(
    val imageRes: Int,
    val currentPrice: Double,
    val originalPrice: Double,
    val description: String,
    val name: String
)
*/

data class Product(
    val id: String?,
    val name: String?,
    val image: String?,
    val price: Double?,
    val priceDesc: Double?,
)