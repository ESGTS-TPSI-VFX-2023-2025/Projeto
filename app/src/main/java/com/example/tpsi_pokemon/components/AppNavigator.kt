package com.example.tpsi_pokemon.components

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cars.com.example.mylogin.CardDisplayScreen
import cars.com.example.mylogin.RecuperarPassScreen
import cars.com.example.mylogin.RegistoScreen
import com.example.tpsi_pokemon.components.scan.BotaoMaiorQuadrado
import com.example.tpsi_pokemon.components.scan.CameraScreen
import com.example.tpsi_pokemon.components.scan.CameraViewModel
import com.example.tpsi_pokemon.components.scan.ChamaAdicionarCarta
import com.example.tpsi_pokemon.components.scan.ChamaCriarColecao
import com.example.tpsi_pokemon.components.scan.ChamaDentroColecao
import com.example.tpsi_pokemon.components.scan.ChamaDentroColecaoPredefinida
import com.example.tpsi_pokemon.components.scan.ChamaEditarColecao
import com.example.tpsi_pokemon.components.scan.ChamaPesquisarColecao
import com.example.tpsi_pokemon.components.scan.Colecao
import com.example.tpsi_pokemon.components.scan.DescricaoCarta
import com.example.tpsi_pokemon.components.scan.DetalhesScreen
import com.example.tpsi_pokemon.components.scan.MenuEscolha
import com.example.tpsi_pokemon.components.scan.POPUPAviso
import com.example.tpsi_pokemon.components.scan.POPUP_apagar
import com.example.tpsi_pokemon.components.scan.POPUPmenu
import com.example.tpsi_pokemon.components.scan.POPUPmenu_Predefinido
import com.example.tpsi_pokemon.components.scan.ShowDetalhesScreen
import com.example.tpsi_pokemon.components.scan.TopbarPrincipal


@Composable
fun AppNavigator(controller: LifecycleCameraController, context: Context) {
    val navController = rememberNavController()
    val viewModel: CameraViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "camera"
    ) {
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("register_screen") {
            RegistoScreen(navController)
        }
        composable("RecPass") {
            RecuperarPassScreen(navController)
        }
        composable("main_menu") {
            CardDisplayScreen(navController)
        }
        composable("camera") {
            CameraScreen(viewModel, navController, controller, context)
        }
        composable("details") {
            val bitmap by viewModel.bitmap.collectAsState()
            if (bitmap != null) {
                DetalhesScreen(viewModel,bitmap!!, navController)
            } else {
                Text(
                    "Image is not ready yet. Please try again.",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
        composable("showdetails") {
            val bitmap by viewModel.bitmap.collectAsState()
            if (bitmap != null) {
                ShowDetalhesScreen(bitmap!!, navController)
            } else {
                Text(
                    "Image is not ready yet. Please try again.",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
        composable("popup") {
            POPUPAviso(navController)
        }
        composable("home") {
            Column {
                TopbarPrincipal(
                    navController = navController,
                    "Coleções",
                    "Criar Coleção",
                    "pesquisar-colecao",
                    "criar_colecao"
                )
                MenuEscolha(navController = navController, Color(0xFF16B4FF), Color(0xFF009BEB))
                val items = List(2) { "Item $it" }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(items.size) { index ->
                        Colecao(
                            navController = navController,
                            "Ditto",
                            "pop-menu-Personalizadas",
                            "entra-colecao"

                        )
                    }
                }

            }
        }

        composable("criar_colecao") {
            ChamaCriarColecao(navController = navController)
        }
        composable("pesquisar-colecao") {
            ChamaPesquisarColecao(navController = navController, "Pesquisar Coleção", "home")
        }
        composable("pesquisar-carta") {
            ChamaPesquisarColecao(
                navController = navController,
                "Pesquisar Carta",
                "entra-colecao"
            )
        }
        composable("pop-menu-Personalizadas/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
            Column(
                modifier = Modifier

                    .blur(10.dp)
            ) {
                TopbarPrincipal(
                    navController = navController,
                    "Coleções",
                    "Criar Coleção",
                    "pesquisar-colecao",
                    "criar_colecao"
                )
                MenuEscolha(navController = navController, Color(0xFF16B4FF), Color(0xFF009BEB))
                val items = List(2) { "Item $it" }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(items.size) { index ->
                        Colecao(
                            navController = navController,
                            "Ditto",
                            "pop-menu-Personalizadas",
                            "entra-colecao"
                        )
                    }
                }

            }
            POPUPmenu(navController = navController, title = name)
        }
        composable("pop-apagar") {
            Column(
                modifier = Modifier

                    .blur(10.dp)
            ) {
                TopbarPrincipal(
                    navController = navController,
                    "Coleções",
                    "Criar Coleção",
                    "pesquisar-colecao",
                    "criar_colecao"
                )
                MenuEscolha(navController = navController, Color(0xFF16B4FF), Color(0xFF009BEB))
                val items = List(2) { "Item $it" }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(items.size) { index ->
                        Colecao(
                            navController = navController,
                            "Ditto",
                            "pop-menu-Personalizadas",
                            "entra-colecao"
                        )
                    }
                }

            }
            POPUP_apagar(
                navController = navController,
                "Tem a certeza que deseja apagar esta Coleção?",
                "home"
            )
        }
        composable("pop-editar") {
            ChamaEditarColecao(navController = navController)
        }
        composable("entra-colecao") {
            ChamaDentroColecao(navController = navController)
        }
        composable("entra-colecao/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
            ChamaDentroColecao(navController = navController, title = name)
        }

        //Carta
        composable("retirar-carta") {
            Column(
                modifier = Modifier

                    .blur(10.dp)
            ) {
                ChamaDentroColecao(navController = navController)

            }
            POPUP_apagar(
                navController = navController,
                "Tem a certeza que deseja retirar esta carta?",
                "entra-colecao"
            )
        }
        composable("descricao-carta") {
            DescricaoCarta(navController = navController)
        }
        composable("adicionar-carta") {
            ChamaAdicionarCarta(navController = navController)
        }

        //Predefinido
        composable("home-predefinido") {
            Column {
                TopbarPrincipal(
                    navController = navController,
                    "Séries",
                    "Adicionar Série",
                    "pesquisar-predefinido",
                    "adicionar-serie"
                )
                MenuEscolha(navController = navController, Color(0xFF009BEB), Color(0xFF16B4FF))
                val items = List(2) { "Item $it" }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(items.size) { index ->
                        Colecao(
                            navController = navController,
                            "Predefinido",
                            "pop-menu-Predefinido",
                            "entra-Predefinida"
                        )
                    }
                }

            }
        }
        composable("pesquisar-predefinido") {
            ChamaPesquisarColecao(
                navController = navController,
                "Pesquisar Série",
                "home-predefinido"
            )
        }
        composable("adicionar-serie") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ChamaPesquisarColecao(
                    navController = navController,
                    "Adicionar Série",
                    "home-predefinido"
                )

                Spacer(modifier = Modifier.weight(1f))

                BotaoMaiorQuadrado("Confirmar")
            }
        }
        composable("pop-menu-Predefinido/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
            Column(
                modifier = Modifier

                    .blur(10.dp)
            ) {
                TopbarPrincipal(
                    navController = navController,
                    "Séries",
                    "Adicionar Série",
                    "pesquisar-predefinido",
                    "adicionar-serie"
                )
                MenuEscolha(navController = navController, Color(0xFF16B4FF), Color(0xFF009BEB))
                val items = List(2) { "Item $it" }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(items.size) { index ->
                        Colecao(
                            navController = navController,
                            "Predefinido",
                            "pop-menu-Predefinido",
                            "entra-Predefinida"
                        )
                    }
                }

            }
            POPUPmenu_Predefinido(navController = navController, title = name)
        }

        composable("pop-apagar-Prededinido") {
            Column(
                modifier = Modifier

                    .blur(10.dp)
            ) {
                TopbarPrincipal(
                    navController = navController,
                    "Séries",
                    "Adicionar Série",
                    "pesquisar-predefinido",
                    "adicionar-serie"
                )
                MenuEscolha(navController = navController, Color(0xFF16B4FF), Color(0xFF009BEB))
                val items = List(2) { "Item $it" }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(items.size) { index ->
                        Colecao(
                            navController = navController,
                            "Predefinido",
                            "pop-menu-Predefinido",
                            "entra-Predefinida"
                        )
                    }
                }

            }
            POPUP_apagar(
                navController = navController,
                "Tem a certeza que deseja retirar esta Série?",
                "home-predefinido"
            )
        }
        composable("entra-Predefinida/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
            ChamaDentroColecaoPredefinida(navController = navController, title = name)
        }
    }
}