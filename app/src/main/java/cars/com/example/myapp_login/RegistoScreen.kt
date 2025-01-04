package cars.com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cars.com.example.myapp_login.R
import com.google.firebase.auth.FirebaseAuth

val arialFontFamily1 = FontFamily(Font(R.font.arial))

@Composable
fun RegistoScreen(navController: NavHostController) {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val repeatPasswordState = remember { mutableStateOf("") }
    val confirmationMessage = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9FB))
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pokedex",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontFamily = arialFontFamily1, color = Color(0xFF16b4ff)),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Avatar Image",
            modifier = Modifier
                .size(120.dp)
                .padding(vertical = 24.dp)
        )

        Text(
            text = "Registo",
            style = TextStyle(fontFamily = arialFontFamily, color = colorResource(id = R.color.azul_normal)),
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Nome  ", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            )
        )

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("E-mail", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            )
        )

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Palavra-passe", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = repeatPasswordState.value,
            onValueChange = { repeatPasswordState.value = it },
            label = { Text("Repetir palavra-passe", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        if (confirmationMessage.value.isNotEmpty()) {
            Text(
                text = confirmationMessage.value,
                color = if (isError.value) Color.Red else Color.Green,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = arialFontFamily),
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Button(
            onClick = {
                if (emailState.value.isNotEmpty() && passwordState.value.isNotEmpty() && repeatPasswordState.value.isNotEmpty()) {
                    if (passwordState.value == repeatPasswordState.value) {
                        auth.createUserWithEmailAndPassword(emailState.value, passwordState.value)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    confirmationMessage.value = "Usuário registrado com sucesso!"
                                    isError.value = false
                                    navController.navigate("login_screen") {
                                        popUpTo("register_screen") { inclusive = true }
                                    }
                                } else {
                                    confirmationMessage.value = "Erro: ${task.exception?.message}"
                                    isError.value = true
                                }
                            }
                    } else {
                        confirmationMessage.value = "As palavras-passe não coincidem!"
                        isError.value = true
                    }
                } else {
                    confirmationMessage.value = "Preencha todos os campos!"
                    isError.value = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
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
fun PreviewRegistoScreen() {
    RegistoScreen(navController = rememberNavController())
}
