import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.ui.theme.Dark
import com.example.app.ui.theme.Orange
import java.math.BigDecimal
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import coil.compose.AsyncImage
import coil.request.ImageRequest

data class GridItem(val id: String, val name: String, val image: String, val price: BigDecimal, val priceDesc: BigDecimal)

data class GridProductItem(val id: String, val name: String, val image: String, val price: BigDecimal, val priceDesc: BigDecimal)
@Composable
fun HomeScreen(navController: NavController?) {
    var productList by remember { mutableStateOf<List<GridProductItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        val products = getProductsFromAPI()
        // Armazenar os produtos no estado do Compose
        productList = products
    }

     /*
    val MOCK_PRODUCTS = listOf(
        GridProductItem(
            "Panolog Pomada Elanco",
            "https://res.cloudinary.com/dxuqupapa/image/upload/v1701736192/Sofisticao_images/sqokblrckdshzyzmuvo3.jpg",
            BigDecimal("98.5"),
            BigDecimal("91.99"),
        ),
        GridProductItem(
            "Antipulgas e Carrapatos Bravecto MSD para Cães",
            "https://res.cloudinary.com/dxuqupapa/image/upload/v1701736192/Sofisticao_images/dnvykd0jzj7jfs0o9hlu.jpg",
            BigDecimal("194.9"),
            BigDecimal("187.99")
        ),
        GridProductItem(
            "Moletom Cansei de Ser Gato Canguru Preto para Gatos",
            "https://res.cloudinary.com/dxuqupapa/image/upload/v1701736193/Sofisticao_images/muaemcn6rgq6a1jleqqm.jpg",
            BigDecimal("79.99"),
            BigDecimal("74.9")
        ),
        GridProductItem(
            "Cama Suspensa Amarela de Janela",
            "https://res.cloudinary.com/dxuqupapa/image/upload/v1701736193/Sofisticao_images/nnuauvgxubwhdlewp6bf.jpg",
            BigDecimal("299.99"),
            BigDecimal("290.99")
        ),
        GridProductItem(
            "Cabana Fábrica Pet",
            "https://res.cloudinary.com/dxuqupapa/image/upload/v1701736192/Sofisticao_images/afr3dyhrmbjnuos00il9.jpg",
            BigDecimal("329.99"),
            BigDecimal("324.99")
        ),
        GridProductItem(
            "Assento Tubline Transpet",
            "https://res.cloudinary.com/dxuqupapa/image/upload/v1701736192/Sofisticao_images/hhlvlxm8cznyw0lnm5ft.jpg",
            BigDecimal("269.99"),
            BigDecimal("188.99")
        )
    )

    productList = MOCK_PRODUCTS
      */

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .fillMaxHeight()
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
        Spacer(modifier = Modifier.height(20.dp))

        // Grid com dois produtos por linha
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .heightIn(200.dp, 800.dp)
                    .background(color = Color.White)
                    .fillMaxWidth()

            ) {

                // Iterate through the product list
                itemsIndexed(productList) { position, _ ->
                    val id = productList[position].id
                    val image = productList[position].image
                    val name = productList[position].name
                    val price = productList[position].price
                    val priceDesc = productList[position].priceDesc

                    GridItemCard(
                        modifier = Modifier.padding(8.dp),
                        item = GridItem(id, name, image, price, priceDesc),
                        navController = navController // Passa o NavController aqui se necessário
                    )


                }
            }




    }


}

@Composable
fun GridItemCard(modifier: Modifier = Modifier, item: GridItem, navController: NavController?) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .width(180.dp)
            .height(260.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.clickable {
                    // Ação ao clicar na imagem
                    navController?.navigate("product/${item.id}")
                }
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
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row() {
                Text(
                    text = "R\$ ${item.price.toString()}",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Dark
                    )
                )
                Spacer(modifier = Modifier.width(13.dp))
                Text(
                    text = item.priceDesc.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                        color = Orange
                    ),
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
