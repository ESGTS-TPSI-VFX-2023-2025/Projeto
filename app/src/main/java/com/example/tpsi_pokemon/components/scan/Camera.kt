@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tpsi_pokemon.components.scan

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme

class Camera : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }
        enableEdgeToEdge()
        setContent {
            TPSI_PokemonTheme {
                Page()
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
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
fun TopBar() {
    // You can implement a top bar here if needed
}

@Composable
fun MiddleSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .paddingFromBaseline(top = 600.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Add the UI elements of the middle section (camera preview, etc.)
        // Top row of blue bars
        Row(
            modifier = Modifier
                .size(300.dp, 150.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left blue corner
            Row {
                Row(
                    modifier = Modifier
                        .size(5.dp, 30.dp)
                        .background(color = Color(0xFF009BEB))

                ) {}
                Row(
                    modifier = Modifier
                        .size(30.dp, 5.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
            }
            // Right blue corner
            Row {
                Row(
                    modifier = Modifier
                        .size(30.dp, 5.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
                Row(
                    modifier = Modifier
                        .size(5.dp, 30.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
            }
        }

        // Middle camera section
        Row(
            modifier = Modifier.size(300.dp, 160.dp), // Fixed height and width for the row
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nested columns for centering
            Column(modifier = Modifier.size(35.dp, 160.dp)) {} // Empty spacer on the left
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .size(230.dp, 160.dp) // Camera section dimensions
                    .background(color = Color(0xFF009BEB)) // Blue background for camera section
            ) {
                // Camera preview or content goes here
                val context = LocalContext.current
                val controller = remember {
                    LifecycleCameraController(context).apply {
                        setEnabledUseCases(
                            CameraController.IMAGE_CAPTURE
                        )
                    }
                }
                CameraPreview(
                    controller = controller,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Column(modifier = Modifier.size(35.dp, 160.dp)) {} // Empty spacer on the right
        }

        // Bottom row of blue bars
        Row(
            modifier = Modifier
                .size(300.dp, 100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            // Left blue corner
            Row(verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .size(5.dp, 30.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
                Row(
                    modifier = Modifier
                        .size(30.dp, 5.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
            }
            // Right blue corner
            Row(verticalAlignment = Alignment.Bottom) {
                Row(
                    modifier = Modifier
                        .size(30.dp, 5.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
                Row(
                    modifier = Modifier
                        .size(5.dp, 30.dp)
                        .background(color = Color(0xFF009BEB))
                ) {}
            }
        }
    }
}


@Composable
fun BottomSection() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .paddingFromBaseline(bottom = 150.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .width(200.dp)
                .height(50.dp),
            onClick = {
                // Launch Detalhes Activity
                val intent = Intent(context, Detalhes::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF009BEB)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "SCAN",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Background() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff4f9fb))
    ) {
        // Draw any background elements if needed
    }
}

@Preview
@Composable
fun Page() {
    Background()
    Column {
        TopBar()
        MiddleSection()
        BottomSection()
    }
}