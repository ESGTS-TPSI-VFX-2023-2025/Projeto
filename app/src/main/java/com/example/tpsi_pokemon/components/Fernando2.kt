package com.example.tpsi_pokemon.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Fernando2(modifier: Modifier = Modifier) {
    Text(
        text = "Fernando",
        modifier = modifier
    )
}

data class Conversa(
    val id: Int,
    val nome: String,
    val mensagens: List<String>,
    var fixada: Boolean = false
)

val conversas = mutableListOf<Conversa>()

fun fixarConversa(conversaId: Int) {
    val conversa = conversas.find { it.id == conversaId }
    conversa?.fixada = true
}
