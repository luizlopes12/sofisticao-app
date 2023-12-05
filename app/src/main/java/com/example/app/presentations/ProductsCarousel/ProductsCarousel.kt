package com.example.app.presentations.ProductsCarousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R

fun ProductsCarousel(navController: NavController?) {
LazyRow(
modifier = Modifier
.fillMaxWidth()
.padding(top = 16.dp),
horizontalArrangement = Arrangement.SpaceBetween
) {
    items(listOf(
        GridItem("Produto 1", R.drawable.product__image),
        GridItem("Produto 2", R.drawable.product__image),
        GridItem("Produto 3", R.drawable.product__image),
        GridItem("Produto 4", R.drawable.product__image),
    )) { item ->
        GridItemCard(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            item = item,
            navController = navController // Opcional, para navegação
        )
    }
}
}

data class GridItem(val name: String, val image: Int)

@Composable
fun GridItemCard(modifier: Modifier, item: GridItem, navController: NavController?) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(painterResource(id = item.image), contentDescription = item.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            if (navController != null) { // Opcional, para navegação
                Button(
                    onClick = {
                        navController.navigate("detail/${item.name}")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ver mais")
                }
            }
        }
    }
}
