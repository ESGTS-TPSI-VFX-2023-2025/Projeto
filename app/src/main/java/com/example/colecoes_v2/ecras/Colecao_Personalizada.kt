package com.example.colecoes_v2.ecras

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Colecao_Personalizada {

}

@Preview
@Composable
fun TopbarPrincipal(){

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(100.dp)
            .background(color = Color.Blue),
            //horizontalArrangement = Arrangement.Center

        ){
            /*Image(
                painter = painterResource(id = R.drawable.divisa_direita),
                contentDescription = "Pokemon",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(0.dp,0.dp,15.dp,0.dp)
                    .height(20.dp)
            )*/
            Text(text = "gamer",color = Color.White);

        }

    }
}

@Preview
@Composable
fun TopbarSecundaria(){

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(100.dp)
            .background(color = Color.Blue),
            horizontalArrangement = Arrangement.End

        ){

            Text(modifier = Modifier.padding(0.dp,0.dp,5.dp,0.dp),
                text = "gamer",color = Color.White);

            /*Image(
                painter = painterResource(id = R.drawable.divisa_direita),
                contentDescription = "Pokemon",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier

                    .height(20.dp)
            )*/


        }

    }
}



@Composable
fun CarPokemon(name : String = "Ditto") {
    Surface() {
        Row(
            modifier = Modifier
                .padding(0.dp, 0.dp, 5.dp, 5.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.Red)
                .fillMaxWidth()

                .height(50.dp)

        ) {
            Row() {
                Column(modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)) {
                    Text(text = name)
                    Text(text = "Type")
                }
                /*Image(
                    painter = painterResource(id = R.drawable.ditto),
                    contentDescription = "Pokemon",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                )*/
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview(){

    Column {

        TopbarPrincipal()

        Text(text = "Pokedex", modifier = Modifier.padding(15.dp,0.dp,0.dp,0.dp))

        val items = List(20) { "Item $it" }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items.size) { index ->
                CarPokemon("Android")
            }
        }

    }
}
