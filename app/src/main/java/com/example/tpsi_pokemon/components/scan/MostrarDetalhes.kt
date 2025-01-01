package com.example.tpsi_pokemon.components.scan

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

import androidx.navigation.NavController


@Composable
fun MoreDetails(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Text(text = "Charizard 1999",
                fontSize = TextUnit(25.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Black,
                color = Color(0xFF009BEB)

            )
        }
        Row() {
            Text(text = "Rarity:",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009BEB)
            )
            Text(text = " Normal",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Normal,
                color = Color(0xFF009BEB),
                textAlign = TextAlign.Right
            )
        }

        Row() {
            Text(text = "Set:",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009BEB),
                textAlign = TextAlign.Right
            )
            Text(text = " Base Set",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Normal,
                color = Color(0xFF009BEB)
            )
        }

        Row() {
            Text(text = "Avaliação:",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009BEB),
                textAlign = TextAlign.Right
            )
            Text(text = " PSA 10",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Normal,
                color = Color(0xFF009BEB)
            )
        }

        Row() {
            Text(text = "Preço:",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009BEB),
                textAlign = TextAlign.Right
            )
            Text(text = " $11,050",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Normal,
                color = Color(0xFF009BEB)
            )
        }
    }
}

@Composable
fun BottomSection3(navController: NavController) {
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
                .height(55.dp),
            onClick = {
                navController.navigate("popup")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF009BEB)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "ADICIONAR À COLEÇÃO",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Composable
fun MiddleSection3(bitmap: Bitmap){
    Row(
        modifier = Modifier
            .height(500.dp)
            .padding(top = 60.dp)
            .padding(vertical = 32.dp)

    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(400.dp),
        ) {
            bitmap?.let { image ->
                Image(
                    bitmap = image.asImageBitmap(), // Convert Bitmap to ImageBitmap here
                    contentDescription = "Captured Image",
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
        }
    }
}


