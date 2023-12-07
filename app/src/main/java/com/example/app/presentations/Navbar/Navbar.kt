// Dentro do pacote com.example.app.presentations.Navbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navbar(navController: NavController) {
    TopAppBar(
        title = {
            // Logo
            Box(
                modifier = Modifier.clickable {
                    // Ação ao clicar na imagem
                    navController?.navigate("home")
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sofisticao_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(120.dp)
                )
            }
        },
        actions = {
            // Icon on the right
            IconButton(
                onClick = { /* Handle icon click */ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic__cart),
                    contentDescription = "Sacola",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .zIndex(1f)
            .padding(horizontal = 10.dp, vertical = 10.dp)
    )
}
