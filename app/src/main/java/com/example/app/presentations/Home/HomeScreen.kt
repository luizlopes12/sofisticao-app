import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.ui.theme.Dark
import com.example.app.ui.theme.Orange
import java.math.BigDecimal

data class GridItem(val name: String, val image: Int)

data class GridProductItem(val name: String, val image: Int, val price: BigDecimal, val priceDesc: BigDecimal)
@Composable
fun HomeScreen(navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "SHOP",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Dark
            ),
            modifier = Modifier
                .padding(top = 35.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .width(60.dp)
                    .height(4.dp)
                    .background(Orange)
            ) {
            }
        }

        // Grid com dois itens por linha
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(
                listOf(
                    GridItem("Produto 1", R.drawable.product__image),
                    GridItem("Produto 2", R.drawable.product__image),
                    GridItem("Produto 3", R.drawable.product__image),
                    GridItem("Produto 4", R.drawable.product__image),
                )
            ) { item ->
                GridItemCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    item = item,
                    navController = navController // Opcional, para navegação
                )
            }
        }

        // Lista de produtos
        val productList = listOf(
            GridProductItem("Produto 5", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
            GridProductItem("Produto 6", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
            GridProductItem("Produto 7", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
            GridProductItem("Produto 8", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
        )

        // Grid com dois produtos por linha
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
        ) {
            itemsIndexed(productList) { position, _ ->
                val image = productList[position].image
                val name = productList[position].name
                val price = productList[position].price
                val priceDesc = productList[position].priceDesc

                Card(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = image!!),
                            contentDescription = null,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp)
                        )
                        Text(
                            text = name!!,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                            )
                        Text(
                            text = price.toString()!!,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                        )
                        Text(
                            text = priceDesc.toString()!!,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }
        }
    }
}


/*
@Composable
fun ProductItem(modifier: Modifier, item: GridItem, navController: NavController?) {
    Card(
        modifier = modifier
            .fillMaxWidth()
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
            Text(
                text = "$50", // Adicione aqui o preço do produto
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            Text(
                text = "Aqui vai uma descrição do preço", // Descrição do preço
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            if (navController != null) {
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
*/

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
                style = androidx.compose.ui.text.TextStyle(
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