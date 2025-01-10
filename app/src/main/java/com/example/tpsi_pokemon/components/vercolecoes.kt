package com.example.tpsi_pokemon.components

import com.example.tpsi_pokemon.R
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.ui.theme.BlueP
import com.example.tpsi_pokemon.ui.theme.BlueB
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme


// Classe para representar as coleções
data class Colecao(val nome: String, val descricao: String, val imagemId: Int)

@Composable
fun VerColecoesScreen(navController: NavController) {
    // Dados de exemplo para as coleções
    val colecoes = listOf(
        Colecao("Coleção 1", "Descrição da Coleção 1", R.drawable.img_4954),
        Colecao("Coleção 2", "Descrição da Coleção 2", R.drawable._86449326_37c2256216_b),
        Colecao("Coleção 3", "Descrição da Coleção 3", R.drawable.foto_branca_download_wallpapers_e_onde_usar_o1fcm9aiph1k9ktn0ul68mv1rcsf)
    )

    // Estado para controlar a caixa selecionada
    val (selectedTab, setSelectedTab) = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("personalizadas") }

    // Layout para exibir as coleções
    Column(modifier = Modifier.fillMaxSize()) {

        // Top Bar
        TopBar1(navController)

        // Título da tela
        Text(
            text = "Coleções",
            fontSize = TextUnit(30f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            color = BlueP,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        // Caixas "Personalizadas" e "Predefinidas" lado a lado com cores dinâmicas
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            // Caixa Personalizadas
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .background(if (selectedTab == "personalizadas") BlueB else BlueP)
                    .clickable { setSelectedTab("personalizadas") },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Personalizadas", fontWeight = FontWeight.Bold, color = Color.White)
            }

            // Caixa Predefinidas
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .background(if (selectedTab == "predefinidas") BlueB else BlueP)
                    .clickable { setSelectedTab("predefinidas") },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Predefinidas", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        // Barra de pesquisa com fundo BlueP e texto branco, com bordas arredondadas
        BasicTextField(
            value = "",
            onValueChange = { /* Atualizar o texto de pesquisa */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(BlueP, shape = androidx.compose.foundation.shape.RoundedCornerShape(30.dp)),
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = TextUnit(16f, TextUnitType.Sp), color = Color.White)
        )

        // Lista de coleções
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(colecoes) { colecao ->
                ColecaoItem(colecao, navController)
            }
        }
    }
}

@Composable
fun TopBar1(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botão de voltar
        BackButton(navController)

        // Centralizar o texto na top bar
        Box(
            modifier = Modifier
                .width(600.dp)
                .padding(0.dp, 0.dp, 40.dp, 0.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(BlueP)
                    .width(200.dp)
                    .height(100.dp)
                    .padding(0.dp, 10.dp),
                contentAlignment = Alignment.Center
            ) {
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

@Composable
fun BackButton(navController: NavController) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(50.dp)),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = { navController.popBackStack() }) {  // Ação de voltar
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun ColecaoItem(colecao: Colecao, navController: NavController) {
    // Layout para exibir cada item da coleção
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(BlueB, shape = RoundedCornerShape(50.dp))
            .padding(16.dp)
            .clickable {
                // Navegar para a tela de Cartas quando o item for clicado
                navController.navigate("vercartas/${colecao.nome}")
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagem da coleção
        Image(
            painter = painterResource(id = colecao.imagemId),
            contentDescription = colecao.nome,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        )

        // Nome da coleção à direita da imagem
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = colecao.nome,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = colecao.descricao,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewVerColecoesScreen() {
    // Criar um NavController simulado para preview
    val navController = rememberNavController()
    VerColecoesScreen(navController)
}