package com.example.tpsi_pokemon.components


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.ui.theme.BlueP
import com.example.tpsi_pokemon.ui.theme.BlueB
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme
import com.example.tpsi_pokemon.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    // Layout da tela inicial, incluindo o botão que navega
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background da app
        Image(
            painter = painterResource(R.drawable.foto_branca_download_wallpapers_e_onde_usar_o1fcm9aiph1k9ktn0ul68mv1rcsf),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Background de perfil (escolhido pelo utilizador)
        Image(
            painter = painterResource(R.drawable._86449326_37c2256216_b),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyRow(modifier = Modifier.padding(95.dp, 0.dp)) {
                item { TopBar(onClick = { navController.popBackStack() }) }
            }
            FotoPerfil()
            Display(navController)
        }
    }
}

@Composable
fun TopBar(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .alpha(0.7f)
                .width(200.dp)
                .height(100.dp)
                .padding(0.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(BlueP)
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable { onClick()},
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Username",
                        fontSize = TextUnit(28f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun FotoPerfil() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)
        .padding(0.dp, 10.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){ }
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(0.dp, 10.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .height(200.dp)
                .width(200.dp)
                .background(Color.White)
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_4954),
                contentDescription = "FotoPerfil",
                modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun Display(navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .height(40.dp)
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            text = "@Username",
            fontSize = TextUnit(24f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            color = BlueP
        )
        Text(
            modifier = Modifier
                .height(40.dp)
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            text = "Nome Completo (opcional)",
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            color = BlueP
        )
        Text(
            modifier = Modifier
                .height(40.dp)
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            text = "Membro desde: (data de criacao de perfil)",
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            color = BlueP
        )
        LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            item { PedidoAmizadeBotao() }
        }
        LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            item { EnviarMsgBotao() }
            item { VerColecoesBotao(navController = navController) }
        }
    }
}

@Composable
fun PedidoAmizadeBotao() {
    // Obter o contexto local para exibir o Toast
    val context = LocalContext.current

    // Layout do botão
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .padding(5.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(BlueB)
                    .height(70.dp)
                    .fillMaxWidth()
                    .clickable {
                        Toast.makeText(context, "Pedido de amizade enviado", Toast.LENGTH_SHORT).show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Adicionar Amigo",
                        fontSize = TextUnit(30f, TextUnitType.Sp),
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    )
                }
            }
        }
    }
}


@Composable
fun EnviarMsgBotao() {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
                .padding(5.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(BlueB)
                    .height(70.dp)
                    .width(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Enviar",
                        fontSize = TextUnit(26f, TextUnitType.Sp),
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier.padding(32.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = "Mensagem",
                        fontSize = TextUnit(26f, TextUnitType.Sp),
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun VerColecoesBotao(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
                .padding(5.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(BlueB)
                    .height(70.dp)
                    .width(300.dp)
                    .clickable {
                        // Navegar para a tela "vercolecoes"
                        navController.navigate("vercolecoes")
                    },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Ver",
                        fontSize = TextUnit(26f, TextUnitType.Sp),
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        modifier = Modifier.padding(34.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = "Colecoes",
                        fontSize = TextUnit(26f, TextUnitType.Sp),
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    )
                }
            }
        }
    }
}
