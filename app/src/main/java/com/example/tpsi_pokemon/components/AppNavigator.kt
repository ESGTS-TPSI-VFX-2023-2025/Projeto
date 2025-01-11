package com.example.tpsi_pokemon.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cars.com.example.mylogin.CardDisplayScreen
import cars.com.example.mylogin.RecuperarPassScreen
import cars.com.example.mylogin.RegistoScreen


@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login_screen"
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
    }
}