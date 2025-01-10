package com.example.tpsi_pokemon.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.R
import com.example.tpsi_pokemon.ui.theme.BlueP
import com.example.tpsi_pokemon.ui.theme.BlueB
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme


// Classe para representar as coleções
data class Carta(val nome: String, val descricao: String, val imagemId: Int)

@Composable
fun VerCartasScreen(navController: NavController, nomeColecao: String) {
    // Dados de exemplo para as coleções
    val cartas = listOf(
        Carta("Carta 1", "Descrição da Carta 1", R.drawable.img_4954),
        Carta("Carta 2", "Descrição da Carta 2", R.drawable._86449326_37c2256216_b),
        Carta("Carta 3", "Descrição da Carta 3", R.drawable.foto_branca_download_wallpapers_e_onde_usar_o1fcm9aiph1k9ktn0ul68mv1rcsf),
        Carta("Carta 4", "Descrição da Carta 4", R.drawable.img_4954),
        Carta("Carta 5", "Descrição da Carta 5", R.drawable._86449326_37c2256216_b),
        Carta("Carta 6", "Descrição da Carta 6", R.drawable.foto_branca_download_wallpapers_e_onde_usar_o1fcm9aiph1k9ktn0ul68mv1rcsf)
    )

    // Estado para controlar a caixa selecionada
    val (selectedTab, setSelectedTab) = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("personalizadas") }

    // Layout para exibir as coleções
    Column(modifier = Modifier.fillMaxSize()) {

        // Top Bar
        TopBar1(navController)

        // Título da tela
        Text(
            text = nomeColecao,
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
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(BlueP, shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão de menu à esquerda do campo de pesquisa
            //IconButton(onClick = { setExpanded(!expanded) }) {
                Icon(
                    painter = painterResource(id = R.drawable.menuhamburguer),  // Certifique-se de ter o ícone
                    contentDescription = "Menu",
                    tint = Color.White
                )
            //}

            // Barra de pesquisa
            BasicTextField(
                value = "",
                onValueChange = { /* Atualizar o texto de pesquisa */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = TextUnit(
                        16f,
                        TextUnitType.Sp
                    ), color = Color.White
                ),
                singleLine = true
            )


        }

        // Lista de coleções
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Divide as coleções em grupos de 3
            items(cartas.chunked(3)) { colecoesChunk ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Exibe cada carta em uma linha
                    colecoesChunk.forEach { carta ->
                        CartaItem(carta)
                    }
                }
            }
        }
    }
}

@Composable
fun CartaItem(colecao: Carta) {
    // Layout para exibir cada item de coleção de forma compacta
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(100.dp)
            .background(BlueB, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .fillMaxWidth(1f), // Reduzindo a largura para 30% da largura total disponível
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem da coleção
        Image(
            painter = painterResource(id = colecao.imagemId),
            contentDescription = colecao.nome,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        // Nome da carta abaixo da imagem
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = colecao.nome,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}


@Composable
fun ListaDeCartas(cartas: List<Carta>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(cartas.chunked(3)) { row ->
            // Cria uma Row para exibir até 3 itens
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Cria um CartaItem para cada item na linha
                row.forEach { carta ->
                    CartaItem(colecao = carta)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVerCartasScreen() {
    // Criar um NavController simulado para preview
    val navController = rememberNavController()
    VerCartasScreen(navController, nomeColecao = "Coleção 1")
}
