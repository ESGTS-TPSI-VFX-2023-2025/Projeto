package com.example.tpsi_pokemon

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.view.LifecycleCameraController
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tpsi_pokemon.components.AppNavigator
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var controller: LifecycleCameraController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, MainActivity.CAMERAX_PERMISSIONS, 0
            )
        }

        // Inicializa o Firebase
        FirebaseApp.initializeApp(this)
        val auth = FirebaseAuth.getInstance()

        enableEdgeToEdge()

        var context = applicationContext;
        // Initialize camera controller here to avoid passing it around
        controller = LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(LifecycleCameraController.IMAGE_CAPTURE)
        }

        setContent {
            TPSI_PokemonTheme {
                AppNavigator(controller,context)
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return MainActivity.CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )
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
