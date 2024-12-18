package cars.com.example.myapp_login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

val arialFontFamily = FontFamily(Font(R.font.arial))

@Composable
fun LoginScreen(navController: NavController) {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pokedex",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontFamily = arialFontFamily, color = colorResource(id = R.color.azul_normal)),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center

        )

        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Image App",
            modifier = Modifier.size(120.dp)
        )

        OutlinedTextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = { Text("Nome/E-mail", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            )
        )

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Palavra-passe", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            )
        )

        Text(
            text = "Esqueceste-te da palavra-passe?",
            style = TextStyle(
                fontFamily = arialFontFamily,
                color = Color(0xFF16b4ff),
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable { navController.navigate("recover_screen") }
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )

        Button(
            onClick = {
                if (usernameState.value.isNotEmpty() && passwordState.value.isNotEmpty()) {
                    navController.navigate("main_menu")
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16b4ff))
        ) {
            Text(
                text = "Login",
                style = TextStyle(fontFamily = arialFontFamily, color = Color.White)
            )
        }

        Button(
            onClick = { navController.navigate("register_screen") },
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16b4ff))
        ) {
            Text(
                text = "Registar utilizador",
                style = TextStyle(fontFamily = arialFontFamily, color = Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}
