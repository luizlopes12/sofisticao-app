import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.ui.theme.Dark
import com.example.app.ui.theme.Orange
import java.math.BigDecimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

// Lista de produtos
/*
val productList = listOf(
    GridProductItem("Produto 5", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
    GridProductItem("Produto 6", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
    GridProductItem("Produto 7", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
    GridProductItem("Produto 8", R.drawable.product__image, BigDecimal(55.55), BigDecimal(75.50)),
)
 */
data class GridItem(val name: String, val image: String, val price: BigDecimal, val priceDesc: BigDecimal)

data class GridProductItem(val name: String, val image: String, val price: BigDecimal, val priceDesc: BigDecimal)
@Composable
fun HomeScreen(navController: NavController?) {
    var productList by remember { mutableStateOf<List<GridProductItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        val products = getProductsFromAPI()
        // Armazenar os produtos no estado do Compose
        productList = products
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "SHOP",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
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

        // Grid com dois produtos por linha
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center,
                //modifier = Modifier.fillMaxSize()
                // modifier = Modifier.fillMaxWidth()
                modifier = Modifier.heightIn(100.dp, 200.dp)
                    .background(color = Color.White)
                    .fillMaxWidth()

            ) {

                // Iterate through the product list
                itemsIndexed(productList) { position, _ ->
                    val image = productList[position].image
                    val name = productList[position].name
                    val price = productList[position].price
                    val priceDesc = productList[position].priceDesc

                    GridItemCard(
                        modifier = Modifier.padding(8.dp),
                        item = GridItem(name, image, price, priceDesc),
                        navController = navController // Passa o NavController aqui se necessário
                    )

                    /*
                    Card(
                        modifier = Modifier.padding(10.dp)
                            .background(color = Color.White)
                    ) {
                        Row(
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
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = name!!,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                                Text(
                                    text = price.toString()!!,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                                Text(
                                    text = priceDesc.toString()!!,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                            }
                        }
                    }

                     */
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
fun GridItemCard(modifier: Modifier = Modifier, item: GridItem, navController: NavController?) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(320.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),
                contentDescription = item.name,
                modifier = Modifier
                    .width(128.dp)
                    .height(132.dp)
                    .clip(MaterialTheme.shapes.small),
                placeholder = ColorPainter(Gray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.price.toString(), // Adicione aqui o preço do produto
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            Text(
                text = item.priceDesc.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Aqui você pode adicionar informações adicionais do produto, como preço, descrição, etc.
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
