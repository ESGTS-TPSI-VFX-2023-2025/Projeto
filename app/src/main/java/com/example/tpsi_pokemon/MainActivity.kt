package com.example.tpsi_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.components.HomeScreen
import com.example.tpsi_pokemon.components.MainScreen
import com.example.tpsi_pokemon.components.UserListScreen
import com.example.tpsi_pokemon.components.VerCartasScreen
import com.example.tpsi_pokemon.components.VerColecoesScreen
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPSI_PokemonTheme {
                // Criação do NavController para navegação
                val navController = rememberNavController()

                // Definição do NavHost para a navegação
                NavHost(navController = navController, startDestination = "pesquisa") {
                    composable("home") {
                        HomeScreen(navController = navController)
                    }
                    composable("vercolecoes") {
                        VerColecoesScreen(navController = navController)
                    }
                    composable("pesquisa") {
                        MainScreen(navController = navController)
                    }
                    composable("userlist") {
                        UserListScreen(navController = navController)
                    }
                    composable("vercartas/{nomeColecao}") { backStackEntry ->
                        val nomeColecao = backStackEntry.arguments?.getString("nomeColecao") ?: ""
                        VerCartasScreen(navController, nomeColecao)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TPSI_PokemonTheme {
        Greeting("Android")
    }
}
