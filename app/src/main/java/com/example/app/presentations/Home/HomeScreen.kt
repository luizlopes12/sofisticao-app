import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.app.presentations.Product.Product
import com.example.app.ui.theme.Dark
import com.example.app.ui.theme.Orange

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