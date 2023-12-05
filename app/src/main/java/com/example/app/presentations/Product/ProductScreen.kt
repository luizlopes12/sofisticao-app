package com.example.app.presentations.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.ui.theme.Dark
import com.example.app.ui.theme.Orange
import getProductByIdFromAPI
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.math.BigDecimal

data class GridProductItem(val id: String, val name: String, val image: String, val price: BigDecimal, val priceDesc: BigDecimal)

@Composable
fun ProductScreen(navController: NavController, productId: String) {
    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(productId) {
        product = getProductByIdFromAPI(productId)
    }

    product?.let { loadedProduct: Product ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Nome do produto
            loadedProduct?.name?.let {
                Text(
                    text = it,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            }

            // Preço atual
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "R\$ ${loadedProduct?.price?.toString()}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Spacer(modifier = Modifier.width(15.dp))
                // Preço original com risco
                Text(
                    text = "R\$ ${loadedProduct?.priceDesc?.toString()}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        color = Orange
                    ),
                    textDecoration = TextDecoration.LineThrough
                )
            }
            // Imagem do produto
            Spacer(modifier = Modifier.height(25.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(loadedProduct?.image)
                    .crossfade(true)
                    .build(),
                contentDescription = loadedProduct?.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
            // Botão Adicionar à Sacola
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Dark),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /* Handle button click */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Dark,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Adicionar à Sacola",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Normal,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic__cart_white),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Detalhes do produto",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Fabricante parceira do Sofisticão.",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                    ),
                )
            }
        }
    }
}
