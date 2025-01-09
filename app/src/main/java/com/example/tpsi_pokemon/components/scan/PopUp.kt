package com.example.tpsi_pokemon.components.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun POPUPAviso(navController: NavController) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        PopupDialog(
            onDismiss = {
                navController.navigate("login_screen")
            },
            onLogin = { navController.navigate("home/scan") },
            Aviso = "Aviso",
            Msg = "Já iniciou sessão?",
            RedButton = "Não",
            BlueButton = "Sim"
        )
    }
}

@Composable
fun PopupDialog(onDismiss: () -> Unit, onLogin: () -> Unit, Aviso: String, Msg: String, RedButton: String, BlueButton: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)), // Semi-transparent background
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Aviso,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 40.dp)
                )
                Text(
                    text = Msg,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 30.dp),
                    color = Color(0xFF009BEB)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(RedButton, color = Color.White)
                    }
                    Button(
                        onClick = onLogin,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009BEB))
                    ) {
                        Text(BlueButton, color = Color.White)
                    }
                }
            }
        }
    }
}





