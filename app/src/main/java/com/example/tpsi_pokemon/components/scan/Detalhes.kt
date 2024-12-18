package com.example.tpsi_pokemon.components.scan

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.util.lerp
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme

class Detalhes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPSI_PokemonTheme {
                Page2()

            }
        }
    }
}

@Composable
fun HorizontalScrollWithAngledImages() {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = Modifier
            .height(500.dp)
            .padding(top = 60.dp)
            .padding(vertical = 32.dp)
    ) {
        items(20) { index ->
            // Get the offset of the current item relative to the center
            val itemInfo = listState.layoutInfo.visibleItemsInfo.find { it.index == index }
            val offset = itemInfo?.let {
                val viewportCenter = listState.layoutInfo.viewportEndOffset / 2
                (it.offset + it.size / 2) - viewportCenter
            } ?: 0

            // Compute rotation and scaling based on the offset
            val rotation = lerp(
                start = -25f, // Angle when off-center
                stop = 0f, // No angle when centered
                fraction = 1 - abs(offset / 400f).coerceIn(0f, 1f)
            )
            val scale = lerp(
                start = 0.8f, // Smaller scale off-center
                stop = 1f, // Full size at center
                fraction = 1 - abs(offset / 400f).coerceIn(0f, 1f)
            )

            // Placeholder blue rectangle with dynamic transformation
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(400.dp)
                    .graphicsLayer(
                        rotationY = rotation,
                        scaleX = scale,
                        scaleY = scale
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawRoundRect(
                        color = Color.Blue,
                        cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
                    )
                }
            }
        }
    }
}

@Composable
private fun Details(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Text(text = "Charizard 1999",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
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
    }
}

@Composable
fun BottomSection2() {
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
                text = "ESCOLHER",
                fontSize = TextUnit(20.05f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MiddleSection2(){
    Column() {

    }


}


@Preview
@Composable
fun Page2(){
    Background()
    Column {
        HorizontalScrollWithAngledImages()
        Details()
        BottomSection2()
    }

}