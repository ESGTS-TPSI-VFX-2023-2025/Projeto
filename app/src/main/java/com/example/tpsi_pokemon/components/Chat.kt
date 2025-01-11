package com.example.tpsi_pokemon

import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Main(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Conversas(navController = navController)
    }
}

//FEITO
@Composable
fun TopBar() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color(0xFF16B4FF)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription ="Pesquisa",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(30.dp)
            )
            Text(
                text = "Pesquise aqui...",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 15.dp)
            )
        }
    }
}

//FEITO
@Preview(showBackground = true)
@Composable
fun Mensagem() {
    TopBar()
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Não existem conversas...",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF03A9F4)
        )
    }
}

//TEST
@Composable
fun Conversas(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopBar()
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp)
        ) {
            Text(
                modifier = Modifier.padding(10.dp, 0.dp),
                text = "Conversas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Lista de conversas
            val conversas = listOf(
                Utilizador("Cloudy", "pinned", true, R.drawable.cloudy, true),
                Utilizador("Rainy", "dá me a tua carta!!!", false, R.drawable.rainy, false),
                Utilizador("Snowy", "vou comprar doritos", true, R.drawable.snowy, false),
                Utilizador("Storm", "lancei música nova", false, R.drawable.storm, false),
                Utilizador("Snowy", "vou comprar doritos", true, R.drawable.snowy, false),
                Utilizador("Storm", "lancei música nova", false, R.drawable.storm, false),
                Utilizador("Snowy", "vou comprar doritos", true, R.drawable.snowy, false),
                Utilizador("Storm", "pinned", false, R.drawable.storm, true),
                Utilizador("Snowy", "vou comprar doritos", true, R.drawable.snowy, false),
                Utilizador("Storm", "lancei música nova", false, R.drawable.storm, false),
                Utilizador("Sun", "já foste", true, R.drawable.sun, false)
            )

            LazyColumn {
                items(conversas.sortedByDescending { it.isPinned }) { conversa ->
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 1.dp)
                    ) {
                        items(listOf(conversa)) { item ->
                            ConversaBotao(item, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
// Botão para cada conversa

@Composable
fun ConversaBotao(utilizador: Utilizador, navController: NavController) {
    Surface( modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)) {

        Button(
            onClick = { navController.navigate("privada") },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (utilizador.cor) Color(0xFF16B4FF) else Color.LightGray
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(width = 375.dp, height = 80.dp) // Dimensões fixas
                .padding(8.dp)

        )

        {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = utilizador.avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = utilizador.nome,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // Cor botão
                    )
                    Text(
                        text = utilizador.mensagem,
                        fontSize = 14.sp,
                        color = Color(0xFFE0E0E0) // Cor do texto secundário
                    )


                }
            }

        }
        if (utilizador.isPinned) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.pin), // Substitua pelo seu ícone de pin
                    contentDescription = "Pin",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(310.dp, 0.dp, 0.dp, 0.dp)
                        .size(50.dp)
                )
            }
        }
    }


}
// dados da conversa
data class Utilizador(
    val nome: String, val mensagem: String, val cor: Boolean, val avatar: Int, val isPinned: Boolean
)


@Composable
fun ConversaPrivada() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color(0xFF16B4FF)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem à esquerda
            Image(
                painter = painterResource(id = R.drawable.portugal),
                contentDescription = "avatar",
                modifier = Modifier
                    .padding(start = 0.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )



            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ImBouncer",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 5.dp)

                )
                Text(
                    text = "Online",
                    color = Color.Green,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrivada(navController: NavController) {
    // Gerenciando o estado da mensagem
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Topo da tela com botão de seta e botão de usuário
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp), // Espaço inferior entre o topo e o conteúdo
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão de seta para voltar
            IconButton(
                onClick = { navController.navigate("back") }, // Navega para Conversas
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.seta_voltar), // Substitua pelo ícone da seta
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a seta e o botão do usuário

            // Botão do usuário (ImBouncer)
            Button(
                onClick = { /* Ação do botão do usuário */
                    navController.navigate("perfil")
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF16B4FF)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar do usuário
                    Image(
                        painter = painterResource(id = R.drawable.portugal), // Substitua pela imagem do avatar
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a imagem e o texto

                    // Nome e status do usuário
                    Column {
                        Text(
                            text = "ImBouncer",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Online",
                            color = Color.Green,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        // Caixa de texto e o botão
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },
                placeholder = { Text("Escreva aqui...") },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE8E8E8),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    println("Mensagem enviada: $message")
                    message = "" // Limpa a mensagem após o envio
                },
                shape = CircleShape,
                modifier = Modifier.size(65.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF16B4FF))
            ) {
                Image(
                    painter = painterResource(id = R.drawable._939961),
                    contentDescription = "Enviar",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
