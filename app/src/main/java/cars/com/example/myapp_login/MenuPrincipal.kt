package cars.com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cars.com.example.myapp_login.R

@Composable
fun CardDisplayScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9FB))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Display de Cartas",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF16b4ff),
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CardItem(drawableId = R.drawable.cartazpokemon)
            }

            Spacer(modifier = Modifier.weight(1f))

            PokeballButtonWithActions(
                onScanClick = { navController.navigate("scan_screen") },
                onYellowClick = { /* Ação para botão amarelo */ },
                onPurpleClick = { /* Ação para botão roxo */ }
            )

            if (showDialog) {
                OptionsDialog(onDismiss = { showDialog = false }, navController = navController)
            }
        }

        CircleButton(
            size = 100.dp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )
    }
}

@Composable
fun PokeballButtonWithActions(
    onScanClick: () -> Unit,
    onYellowClick: () -> Unit,
    onPurpleClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .offset(y = 80.dp)
                    .size(120.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokebola1),
                    contentDescription = "Pokébola - Menu",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(100.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-80).dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onYellowClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Chat", color = Color.Black)
            }
            Button(
                onClick = onPurpleClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF800080)),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Coleções", color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-20).dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onScanClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Scan", color = Color.White)
            }
        }
    }
}

@Composable
fun OptionsDialog(onDismiss: () -> Unit, navController: NavController) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Escolha uma opção", style = MaterialTheme.typography.headlineMedium)
        },
        text = {
            Column {
                TextButton(onClick = {
                    navController.navigate("scan_screen")
                    onDismiss()
                }) {
                    Text(text = "Scan", style = MaterialTheme.typography.bodyLarge)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar")
            }
        }

    )
}

@Composable
fun CircleButton(size: Dp, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16b4ff)),
        modifier = modifier.size(size)
    ) {
        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = "Perfil",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CardItem(drawableId: Int) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "Carta de exibição",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .aspectRatio(0.7f)
            .padding(vertical = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCardDisplayScreen() {
    CardDisplayScreen(navController = rememberNavController())
}
