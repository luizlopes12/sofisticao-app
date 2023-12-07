import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.app.R

@Composable
fun HomeScreen(navController: NavController?) {
    val context = LocalContext.current;
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
            .fillMaxHeight()
    ) {

        Text(
            text = "BEST SELLERS",
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

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemsIndexed(productList) { position, _ ->
                val id = productList[position].id
                val image = productList[position].image
                val name = productList[position].name
                val price = productList[position].price
                val priceDesc = productList[position].priceDesc

                GridItemCard2(
                    modifier = Modifier.padding(8.dp),
                    item = GridItem(id, name, image, price, priceDesc),
                    navController = navController
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sofisticao_illustrative),
                contentDescription = "Descrição da imagem",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Ajusta a proporção da imagem
                    .padding(horizontal = 32.dp, vertical = 8.dp) // Ajusta o padding horizontal da imagem
            )
            Text(
                text = "Sobre nós",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp, top = 8.dp), // Ajusta o padding do texto "Sobre nós" e alinha à esquerda
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Text(
                text = "Somos uma marca que preza pela individualidade do seu pet. Cuidamos do estilo e bem estar do seu amiguinho num life style simples e leve. Siga-nos nas redes sociais e acompanhe as novidades.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp), // Ajusta o padding do texto de baixo
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .background(Dark),
                horizontalArrangement = Arrangement.Center
            ) {
                InstagramButton(context = context)
            }
        }
        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sofisticao_illustrative),
                contentDescription = "Descrição da imagem",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Sobre nós",
                modifier = Modifier
                .padding(bottom = 16.dp)
            )
            Text(
                text = "Somos uma marca que preza pela individualidade do seu pet. Cuidamos do estilo e bem estar do seu amiguinho num life style simples e leve. Siga-nos nas redes sociais e acompanhe as novidades.",
                modifier = Modifier
                    .padding(bottom = 14.dp)
            )
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
                        .height(60.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "IR PARA O INSTAGRAM",
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
                            painter = painterResource(id = R.drawable.ic__instagram),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            }
        }
         */







    }


}

@Composable
fun GridItemCard2(modifier: Modifier = Modifier, item: GridItem, navController: NavController?) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .width(180.dp)
            .height(300.dp)
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
            Column {
                Text(
                    text = item.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.fillMaxWidth().heightIn(min = 36.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "R\$ ${item.price.toString()}",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Dark
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "R\$ ${item.priceDesc.toString()}",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                        color = Orange
                    ),
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Botão Adicionar à Sacola
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Dark),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {navController?.navigate("shop")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Dark,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        // .height(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ver mais",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Normal,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InstagramButton(context: Context) {
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.instagram.com/sofisticao_shop")

            // Tenta abrir no navegador
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Se falhar, exibe uma mensagem de erro ou lida com isso conforme necessário
                // Pode adicionar um Toast ou log para informar que não há navegador disponível
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Dark,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "IR PARA O INSTAGRAM",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                ),
                modifier = Modifier.weight(1f)

            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic__instagram),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}

