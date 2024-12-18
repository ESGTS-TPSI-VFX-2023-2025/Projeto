package cars.com.example.myapp_login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cars.com.example.myapp_login.ui.theme.Myapp_loginTheme
import cars.com.example.mylogin.CardDisplayScreen
import cars.com.example.mylogin.RecuperarPassScreen
import cars.com.example.mylogin.RegistoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Myapp_loginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

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

        composable("recover_screen") {
            RecuperarPassScreen(navController)
        }

        composable("main_menu") {
            CardDisplayScreen(navController)
        }
    }
}
