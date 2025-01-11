package cars.com.example.mylogin

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.R
import com.google.firebase.auth.FirebaseAuth

val arialFontFamily = FontFamily(Font(R.font.arial))

@Composable
fun RecuperarPassScreen(navController: NavHostController) {
    val emailState = remember { mutableStateOf("") }
    val newPasswordState = remember { mutableStateOf("") }
    val repeatPasswordState = remember { mutableStateOf("") }
    val confirmationMessage = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar palavra-passe",
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
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Nome / E-mail", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF16b4ff),
                unfocusedBorderColor = Color(0xFF16b4ff)
            )
        )

        OutlinedTextField(
            value = newPasswordState.value,
            onValueChange = { newPasswordState.value = it },
            label = { Text("Nova Palavra-passe", style = TextStyle(fontFamily = arialFontFamily)) },
            modifier = Modifier.fillMaxWidth(),
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
            modifier = Modifier.fillMaxWidth(),
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
                if (emailState.value.isNotEmpty() && newPasswordState.value.isNotEmpty() && repeatPasswordState.value.isNotEmpty()) {
                    if (newPasswordState.value == repeatPasswordState.value) {
                        val email = emailState.value
                        val newPassword = newPasswordState.value


                        val user = auth.currentUser
                        if (user != null && user.email == email) {

                            user.updatePassword(newPassword)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        confirmationMessage.value = "Palavra-passe alterada com sucesso!"
                                        isError.value = false


                                        auth.signInWithEmailAndPassword(email, newPassword)
                                            .addOnCompleteListener { loginTask ->
                                                if (loginTask.isSuccessful) {
                                                    // Login bem-sucedido
                                                    navController.navigate("login_screen")
                                                } else {
                                                    confirmationMessage.value = "Erro ao fazer login com a nova senha!"
                                                    isError.value = true
                                                }
                                            }

                                    } else {
                                        confirmationMessage.value = "Erro ao alterar a palavra-passe!"
                                        isError.value = true
                                    }
                                }
                        } else {
                            confirmationMessage.value = "Nome / E-mail não encontrado !"
                            isError.value = true
                        }
                    } else {
                        confirmationMessage.value = "As palavras-passe não coincidem!"
                        isError.value = true
                    }
                } else {
                    confirmationMessage.value = "Por favor, preencha todos os campos!"
                    isError.value = true
                }
            },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF16b4ff),
            )
        ) {
            Text(
                text = "Alterar palavra-passe",
                style = TextStyle(fontFamily = arialFontFamily, color = Color.White)
            )
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB0BEC5),
            )
        ) {
            Text(
                text = "Cancelar",
                style = TextStyle(fontFamily = arialFontFamily, color = Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecuperarPassScreen() {
    RecuperarPassScreen(navController = rememberNavController())
}
