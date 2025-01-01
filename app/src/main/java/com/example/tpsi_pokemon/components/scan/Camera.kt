package com.example.tpsi_pokemon.components.scan

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.text.font.FontWeight
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.compose.ui.draw.clip
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.R
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme

class Camera : ComponentActivity() {
    private lateinit var controller: LifecycleCameraController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }
        enableEdgeToEdge()

        // Initialize camera controller here to avoid passing it around
        controller = LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(LifecycleCameraController.IMAGE_CAPTURE)
        }

        setContent {
           TPSI_PokemonTheme{
                AppNavHost(controller)

            }
        }
    }

    @Composable
    fun AppNavHost(controller: LifecycleCameraController) {
        val navController = rememberNavController()
        val viewModel: CameraViewModel = viewModel()

        NavHost(navController = navController, startDestination = "camera") {
            composable("camera") {
                CameraScreen(viewModel, navController)
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
        }
    }


    @Composable
    fun CameraScreen(viewModel: CameraViewModel,navController: NavController){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(navController, "main_menu")
            MiddleSection(controller)
            BottomSection(viewModel,navController)
        }
    }

    @Composable
    fun ShowDetalhesScreen(capturedBitmap: Bitmap, navController: NavController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(navController, "details")
            MiddleSection3(capturedBitmap)
            Details()
            BottomSection3(navController)
        }
    }

    @Composable
    fun DetalhesScreen(viewModel: CameraViewModel,capturedBitmap: Bitmap, navController: NavController) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBar(navController, "camera")
            HorizontalScrollWithAngledImages(capturedBitmap)
            MoreDetails()
            BottomSection2(viewModel,capturedBitmap, navController)
        }
    }

    fun takePhoto(
        viewModel: CameraViewModel,
        navController: NavController

    ) {
        controller.takePicture(
            ContextCompat.getMainExecutor(applicationContext),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }
                    val rotatedBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )

                    viewModel.onTakePhoto(rotatedBitmap)
                    navController.navigate("details")
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Couldn't take photo: ", exception)
                }
            }
        )
    }

    @Composable
    fun BottomSection(viewModel: CameraViewModel, navController: NavController) {
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
                    takePhoto(viewModel, navController) // Trigger photo capture
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF009BEB)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "SCAN",
                    fontSize = TextUnit(20.05f, TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }


    @Composable
    fun MiddleSection(controller: LifecycleCameraController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .paddingFromBaseline(top = 600.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Row(
                modifier = Modifier.size(300.dp, 160.dp), // Fixed height and width for the row
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.size(35.dp, 160.dp)) {} // Empty spacer on the left
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(230.dp, 160.dp) // Camera section dimensions
                        .background(color = Color(0xFF009BEB)) // Blue background for camera section
                ) {
                    CameraPreview(
                        controller = controller,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(modifier = Modifier.size(35.dp, 160.dp)) {} // Empty spacer on the right
            }

            Row(
                modifier = Modifier
                    .size(300.dp, 100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
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
fun TopBar(navController: NavController, string: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        IconButton(
            onClick = {
                navController.navigate(string)
            },
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.left),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}