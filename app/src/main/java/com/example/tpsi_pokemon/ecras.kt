package com.example.projeto


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.focusTarget
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


@Composable
fun TopbarPrincipal(navController: NavController, title: String = "Coleção",opcao1:String, routePesquisa : String,routeAdd : String) {
    var clickCriar by remember { mutableStateOf(false) }
    var clickPesquisar by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Surface() {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp, 15.dp, 0.dp, 15.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .width(350.dp)
                    .height(50.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(40.dp))
                    .background(color = Color(0xFF16B4FF)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(55.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f)
                )

                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tres_pontos),
                        contentDescription = "Opções",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .fillMaxHeight()

                    )
                }


            }

        }
        Surface(modifier = Modifier
            .padding(100.dp,70.dp,0.dp,0.dp)) {

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(0xFF009BEB))
                    .width(250.dp),


                ){
                DropdownMenuItem(
                    leadingIcon = {Image(
                        painter = painterResource(id = R.drawable.botao_adicionar),
                        modifier = Modifier
                            .padding(end = 1.dp, start = 8.dp)
                            .size(38.dp),
                        contentDescription = "Ícone de Pesquisa"
                    )},
                    text = { Text(text = opcao1, fontSize = 20.sp) },
                    onClick = {
                        navController.navigate(routeAdd)
                        expanded = false },

                    )
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                )
                DropdownMenuItem(
                    leadingIcon = {Image(
                        painter = painterResource(id = R.drawable.lupa),
                        modifier = Modifier
                            .padding(end = 1.dp, start = 8.dp)
                            .size(38.dp),
                        contentDescription = "Ícone de Pesquisa"
                    )},
                    text = { Text(text = "Pesquisar", fontSize = 20.sp) },
                    onClick = {
                        navController.navigate(routePesquisa )
                        expanded = false },
                )

            }
        }
    }
}


@Composable
fun Topbarbold(title: String = "Pesquisar Coleções", navController: NavHostController, route: String = "home"){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .padding(0.dp, 15.dp, 0.dp, 15.dp)
            .clip(RoundedCornerShape(40.dp))
            .width(350.dp)
            .height(50.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(40.dp))
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.divisa_direita__1_),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(end = 1.dp, start = 8.dp)
                    .size(38.dp)
                    .clickable {
                        navController.navigate(route)
                    }
            )

            Text(
                text = title,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp),



                )

        }

    }
}

@Composable
fun TopbarSecundária(title: String = "Pesquisar Coleções", navController: NavHostController, route: String = "entra-colecao"){
    var expanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .padding(0.dp, 15.dp, 0.dp, 10.dp)
            .clip(RoundedCornerShape(40.dp))
            .width(350.dp)
            .height(50.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(40.dp))
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){

            Image(
                painter = painterResource(id = R.drawable.divisa_direita__1_),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(end = 1.dp, start = 8.dp)
                    .size(38.dp)
                    .clickable {
                        navController.navigate(route)
                    }
            )

            Text(
                text = title,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f),

            )
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tres_pontos),
                    contentDescription = "Opções",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()

                )
            }
        }
    }
            Surface(modifier = Modifier
                .padding(130.dp,0.dp,0.dp,0.dp)) {

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color(0xFF009BEB))
                        .width(250.dp),


                    ){
                    DropdownMenuItem(
                        leadingIcon = {Image(
                            painter = painterResource(id = R.drawable.botao_adicionar),
                            modifier = Modifier
                                .padding(end = 1.dp, start = 8.dp)
                                .size(38.dp),
                            contentDescription = "Adicionar Carta"
                        )},
                        text = { Text(text = "Adicionar Carta", fontSize = 20.sp) },
                        onClick = {
                            navController.navigate("adicionar-carta")
                            expanded = false },

                        )
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.Black)
                    )
                    DropdownMenuItem(
                        leadingIcon = {Image(
                            painter = painterResource(id = R.drawable.lupa),
                            modifier = Modifier
                                .padding(end = 1.dp, start = 8.dp)
                                .size(38.dp),
                            contentDescription = "Pesquisar Carta"
                        )},
                        text = { Text(text = "Pesquisar", fontSize = 20.sp) },
                        onClick = {
                            navController.navigate("pesquisar-carta")
                            expanded = false },
                    )

                }
            }

}

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PesquisarBar(){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .padding(0.dp, 15.dp, 0.dp, 15.dp)
            .clip(RoundedCornerShape(40.dp))
            .width(300.dp)
            .height(50.dp)
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,


            ){
            Image(
                painter = painterResource(id = R.drawable.lupa),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(end = 1.dp, start = 8.dp)
                    .size(35.dp)
            )
            val textState = remember { androidx.compose.runtime.mutableStateOf("") }
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Pesquisar...") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF16B4FF) // Define a cor de fundo
                ),
            )


        }

    }
}


@Composable
fun Colecao(navController: NavController,name: String = "Nova Coleção",routeMenu: String,routeEntra : String) {
    Surface {
        Row(
            modifier = Modifier
                .padding(0.dp, 0.dp, 5.dp, 20.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color = Color(0xFF16B4FF))
                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .height(70.dp)
                .clickable { navController.navigate("$routeEntra/$name") },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Image(
                painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(start = 19.dp)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = name,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.tres_pontos),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(35.dp)
                    .padding(end = 8.dp)
                    .fillMaxHeight()
                    .clickable {
                        navController.navigate("$routeMenu/$name")
                    },
            )


        }
    }
}


@Composable
fun MenuEscolha(navController: NavController, color1 : Color, color2: Color){
    Row(modifier = Modifier.fillMaxWidth() ) {

        Row(modifier = Modifier
            .background(color = color1)
            .weight(1f)
            .clickable {
                navController.navigate("home")
            }, verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center ) {
            Text(
                text = "Personalizada",
                color = Color.White,
                textAlign = TextAlign.Center,

                )
        }
        Row(modifier = Modifier
            .background(color = color2)
            .weight(1f)
            .clickable {
                navController.navigate("home-predefinido")
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Predifinida",
                color = Color.White,
                textAlign = TextAlign.Center,

                )
        }
    }
}

//@Preview
@Composable
fun SubMenu(){
    Column(modifier = Modifier
        .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .height(90.dp)
        .width(220.dp)
        .background(color = Color(0xFF009BEB))

    ){
        //Primeira opção "Criar Coleção"

        Row(modifier = Modifier
            .border(2.dp, Color.Black)
            .fillMaxHeight()
            .fillMaxWidth()
            .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.botao_adicionar),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(start = 19.dp)
                    .fillMaxHeight()
            )


            Text(
                text = "Criar Coleção",
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        //Segunda opção "Pesquisar"
        Row(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.lupa),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(start = 19.dp)
                    .fillMaxHeight()
            )

            Text(
                text = "Pesquisar",
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

//@Preview
@Composable
fun CenaGrande(name: String = "Inserir Imagem", altura: Int = 240, comprimento: Int = 310, imagem: Int = 125, letra: Int = 113) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(

            modifier = Modifier
                .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .height(altura.dp)
                .width(comprimento.dp)
                .background(color = Color(0xFF16B4FF)),


            ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.upload_big_arrow),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(imagem.dp)

                )


            }

            Row(
            ) {
                Text(modifier = Modifier.padding(start = letra.dp, bottom = 20.dp), color = Color.White,
                    text = name

                )
            }
        }
    }
}

//@Preview
@Composable
fun TituloBold() {
    Row(modifier = Modifier.padding(start = 50.dp, top = 100.dp)) {
        Text(
            text = "Título",
            color = Color.Black,
            fontSize = 30.sp
        )
    }
}

//@Preview
@Composable
fun BotaoPequeno(name: String = "Criar"){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .padding(0.dp, 90.dp, 0.dp, 15.dp)
            .clip(RoundedCornerShape(40.dp))
            .width(175.dp)
            .height(50.dp)
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,


            ){
            Text(text = name,color = Color.White,fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() );

        }

    }
}


//@Preview
@Composable
fun FraseSublinhada() {
    val textState = remember { androidx.compose.runtime.mutableStateOf("") }

    Column(modifier = Modifier
        .padding(start = 50.dp, top = 5.dp)) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Título") },
        )
    }
}

@Composable
fun CartaPredefinida(navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    var backgroundColor by remember { mutableStateOf(Color(0xFF16B4FF)) }

    Column(modifier = Modifier
        .padding(end = 10.dp,bottom = 10.dp, start = 10.dp)
        .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .height(220.dp)
        .fillMaxWidth()
        .background(color = backgroundColor)
        .clickable {
            // Altere a cor ao clicar na Row
            backgroundColor = if (backgroundColor == Color(0xFF16B4FF)) {
                Color(0xFF808080)
            } else {
                Color(0xFF16B4FF)
            }
        },


        ){
        Image(
            painter = painterResource(id = R.drawable.defaultimage),
            contentDescription = "Opções",
            modifier = Modifier
                .height(180.dp)

        )
        Row {
            Text(
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 15.dp),
                fontSize = 25.sp,
                color = Color.White,
                text = "Carta 1"


            )
            Surface(modifier = Modifier
                .padding(0.dp,35.dp,0.dp,0.dp)) {

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color(0xFF009BEB))
                        .width(200.dp),


                    ){
                    DropdownMenuItem(
                        leadingIcon = {Image(
                            painter = painterResource(id = R.drawable.excluir),
                            modifier = Modifier
                                .padding(end = 1.dp, start = 8.dp)
                                .size(30.dp),
                            contentDescription = "Retirar Carta"
                        )},
                        text = { Text(text = "Retirar", fontSize = 20.sp) },
                        onClick = {
                            navController.navigate("retirar-carta")
                            expanded = false },

                        )
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.Black)
                    )
                    DropdownMenuItem(
                        leadingIcon = {Image(
                            painter = painterResource(id = R.drawable.defaultimage),
                            modifier = Modifier
                                .padding(end = 1.dp, start = 8.dp)
                                .size(30.dp),
                            contentDescription = "Descrição da Carta"
                        )},
                        text = { Text(text = "Descrição", fontSize = 20.sp) },
                        onClick = {
                            navController.navigate("descricao-carta")
                            expanded = false },
                    )

                }
            }
        }

    }
}
@Composable
fun Carta(navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .padding(end = 10.dp,bottom = 10.dp, start = 10.dp)
        .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .height(220.dp)
        .fillMaxWidth()
        .background(color = Color(0xFF009BEB))


    ){
        Image(
            painter = painterResource(id = R.drawable.defaultimage),
            contentDescription = "Opções",
            modifier = Modifier
                .height(180.dp)

        )
        Row {
            Text(
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 15.dp),
                fontSize = 25.sp,
                color = Color.White,
                text = "Carta 1"


            )
            Surface(modifier = Modifier
                .padding(0.dp,35.dp,0.dp,0.dp)) {

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color(0xFF009BEB))
                        .width(200.dp),


                    ){
                    DropdownMenuItem(
                        leadingIcon = {Image(
                            painter = painterResource(id = R.drawable.excluir),
                            modifier = Modifier
                                .padding(end = 1.dp, start = 8.dp)
                                .size(30.dp),
                            contentDescription = "Retirar Carta"
                        )},
                        text = { Text(text = "Retirar", fontSize = 20.sp) },
                        onClick = {
                            navController.navigate("retirar-carta")
                            expanded = false },

                        )
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.Black)
                    )
                    DropdownMenuItem(
                        leadingIcon = {Image(
                            painter = painterResource(id = R.drawable.cardapio),
                            modifier = Modifier
                                .padding(end = 1.dp, start = 8.dp)
                                .size(30.dp),
                            contentDescription = "Descrição da Carta"
                        )},
                        text = { Text(text = "Descrição", fontSize = 20.sp) },
                        onClick = {
                            navController.navigate("descricao-carta")
                            expanded = false },
                    )

                }
            }
            Image(
                painter = painterResource(id = R.drawable.tres_pontos),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(35.dp)
                    .padding(start = 18.dp)
                    .clickable { expanded = true }

            )
        }

        }
    }




//@Preview
@Composable
fun ImagemEditar(){
    Image(
        painter = painterResource(id = R.drawable.defaultimage),
        contentDescription = "Imagem",
        modifier = Modifier.padding(start = 100.dp),



        )

}
@Composable
fun POPUPmenu_Predefinido(navController: NavController,title: String){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(modifier = Modifier
            .border(4.dp, Color.Black)
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(color = Color(0xFF16B4FF)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Image(
                painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Imagem",
                modifier = Modifier
                    .height(60.dp)
                    .padding(0.dp, 10.dp, 0.dp, 5.dp)
                    .clickable {
                        navController.navigate("home-predefinido")
                    }
            )

            Row(
                modifier = Modifier
                    .border(2.dp, Color.Black),
                horizontalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultimage),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(80.dp)
                )


                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }

            Row(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultimage),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f)
                )


                Text(
                    text = "Apagar",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate("pop-apagar-Prededinido")
                        }

                )
            }

        }
    }

}
@Composable
fun POPUPmenu(navController: NavController,title : String){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(modifier = Modifier
            .border(4.dp, Color.Black)
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(color = Color(0xFF16B4FF)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Image(
                painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Imagem",
                modifier = Modifier
                    .height(60.dp)
                    .padding(0.dp, 10.dp, 0.dp, 5.dp)
                    .clickable {
                        navController.navigate("home")
                    }
            )

            Row(
                modifier = Modifier
                    .border(2.dp, Color.Black),
                horizontalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultimage),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(80.dp)
                )


                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }

            Row(
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultimage),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f)
                )


                Text(
                    text = "Editar",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .clickable {navController.navigate("pop-editar")
                        }
                )
            }

            Row(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultimage),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f)
                )


                Text(
                    text = "Apagar",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate("pop-apagar")
                        }

                )
            }

        }
    }

}

@Composable
fun LinhaPreta(){
    Row(
        modifier = Modifier.padding(top = 275.dp),
        horizontalArrangement = Arrangement.Center,

        ) {
        Box(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.Black),
        )
    }

}

@Composable
fun BotaoMaiorQuadrado(name: String = "Usar Scan"){
    Row(modifier = Modifier
        .padding(start = 20.dp, top = 120.dp)
        .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier

            .clip(RoundedCornerShape(10.dp))
            .width(350.dp)
            .height(75.dp)
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,


            ){
            Text(text = name,color = Color.White,fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() );

        }

    }
}

@Preview
@Composable
fun BotaoMaiorRedondo(name: String = "Usar Scan"){
    Row(modifier = Modifier

        .padding(start = 20.dp, top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .border(1.dp, Color.Black, RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .width(350.dp)
            .height(75.dp)
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,


            ){
            Text(text = name,color = Color.White,fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() );

        }

    }
}


@Composable
fun POPUP_apagar(navController: NavController,texto : String, route :String ){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(30.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(250.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Text(
                text = "AVISO",
                color = Color.Red,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp,0.dp,0.dp,50.dp)
            )

            Text(
                text = texto,
                color = Color(0xFF16B4FF),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(150.dp)
                    .padding(0.dp, 0.dp, 0.dp, 30.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically ){
                Text(
                    text = "Voltar",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .height(30.dp)
                        .width(150.dp)
                        .background(color = Color(0xFF16B4FF))
                        .wrapContentHeight()
                        .clickable {navController.navigate(route)},
                )
                Text(
                    text = "Apagar",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .height(30.dp)
                        .width(150.dp)
                        .background(color = Color.Red)
                        .wrapContentHeight()
                        .clickable {navController.navigate(route)},
                )

            }

        }
    }

}





@Composable
fun ChamaPesquisarColecao(navController: NavHostController,titulo:String,route: String = "home"){
    Column() {

        Topbarbold(titulo, navController,route)
        PesquisarBar()
    }
}


@Composable
public fun ChamaCriarColecao(navController: NavHostController) {
    Column {
        Topbarbold("Criar Coleção", navController ,"home")
        CenaGrande("Inserir Imagem",240, 310, 125)
        TituloBold()
        FraseSublinhada()
        BotaoPequeno("Criar")
    }

}

@Composable
fun ChamaEditarColecao(navController: NavHostController){
    Column(){
        Topbarbold("Editar",navController,"home")
        ImagemEditar()
        CenaGrande("Alterar Imagem", 170, 250, 70, 80)
        TituloBold()
        FraseSublinhada()
        BotaoPequeno("Guardar")
    }
}

@Composable
fun DescricaoCarta(navController: NavHostController){
    Column {
        TopbarboldSemBotao("Mais Caras");
        MiddleBarDescricao(navController);
        CartaDetalhes();
        Row{
            TituloBoldAzul("Raridade:");
            TituloAzul("Raridade");
        }
        Row{
            TituloBoldAzul("Set:");
            TituloAzul("Set");
        }

    }
}

@Composable
fun TopbarboldSemBotao(title: String = "Pesquisar Coleções"){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .padding(0.dp, 15.dp, 0.dp, 15.dp)
            .clip(RoundedCornerShape(40.dp))
            .width(350.dp)
            .height(50.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(40.dp))
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){

            Text(
                text = title,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp),



                )

        }

    }
}

@Composable
fun TituloAzul(nome: String = "Raridade") {
    Row(modifier = Modifier
        .height(50.dp)
        .padding(start = 5.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center) {
        Text(
            text = nome,
            color = Color(0xFF16B4FF),
            fontSize = 20.sp
        )
    }
}

@Composable
fun TituloBoldAzul(nome: String = "Raridade") {
    Row(modifier = Modifier
        .height(50.dp)
        .padding(start = 90.dp, top = 10.dp)) {
        Text(
            fontWeight = FontWeight.Bold,
            text = nome,
            color = Color(0xFF16B4FF),
            fontSize = 30.sp
        )
    }
}

@Composable
fun MiddleBarDescricao(navController: NavHostController,title: String = "Charizard"){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp)
            .clip(RoundedCornerShape(40.dp))
            .width(180.dp)
            .height(50.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(40.dp))
            .background(color = Color(0xFF16B4FF)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.divisa_direita__1_),
                contentDescription = "Opções",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(end = 1.dp, start = 8.dp)
                    .size(38.dp)
                    .clickable {navController.navigate("entra-colecao")
                    }
            )

            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp),



                )

        }

    }
}

@Composable
fun CartaDetalhes() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(

            modifier = Modifier
                .border(2.dp, Color.Black, RoundedCornerShape(0.dp))
                .height(220.dp)
                .width(220.dp)
                .background(color = Color(0xFF16B4FF)),


            ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.upload_big_arrow),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .height(140.dp)

                )


            }
        }
    }
}



@Composable
fun ChamaDentroColecao(navController: NavHostController,title: String = "Coleção"){
    Column(){
        TopbarSecundária(title,navController = navController, "home")
        val items = List(6) { "Item $it" }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(items.size) { index ->
                Carta(navController = navController)
            }
        }
    }

}


@Composable
fun ChamaAdicionarCarta(navController: NavHostController){
    Column {
        Topbarbold("Adicionar Carta",navController,"entra-colecao");
        PesquisarBar();
        LinhaPreta();
        BotaoMaiorRedondo("Usar Scan");
        BotaoMaiorQuadrado("Confirmar");
    }
}

@Composable
fun ChamaDentroColecaoPredefinida(navController: NavHostController,title: String = "Silver Tempest"){
    Column(){
        Topbarbold(title,navController = navController, "home-predefinido")
        val items = List(6) { "Item $it" }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(items.size) { index ->
                CartaPredefinida(navController = navController)

            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Column(modifier = Modifier.fillMaxHeight()) {
        //ChamaPrincipal()
        //ChamaPesquisarColecao()
        //ChamaCriarColecao()
        //ChamaEditarColecao()
        //ChamaDentroColecao()
    }
}

@Composable
fun ChamaPrincipal(){
    val navController = rememberNavController()
    Column() {

        NavHost(navController, startDestination = "home", modifier = Modifier.fillMaxSize()) {
            //Coleções do utilizador
            composable("home") {
                Column {
                    TopbarPrincipal(navController = navController, "Coleções","Criar Coleção","pesquisar-colecao","criar_colecao")
                    MenuEscolha(navController = navController,Color(0xFF16B4FF),Color(0xFF009BEB))
                    val items = List(2) { "Item $it" }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items.size) { index ->
                            Colecao(navController = navController,"Ditto","pop-menu-Personalizadas","entra-colecao")
                        }
                    }

                }
            }
            composable("criar_colecao") {
                ChamaCriarColecao(navController = navController)
            }
            composable("pesquisar-colecao") {
                ChamaPesquisarColecao(navController = navController,"Pesquisar Coleção","home")
            }
            composable("pesquisar-carta") {
                ChamaPesquisarColecao(navController = navController,"Pesquisar Carta","entra-colecao")
            }
            composable("pop-menu-Personalizadas/{name}") {backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
                Column(modifier = Modifier

                    .blur(10.dp)) {
                    TopbarPrincipal(navController = navController, "Coleções","Criar Coleção","pesquisar-colecao","criar_colecao")
                    MenuEscolha(navController = navController,Color(0xFF16B4FF),Color(0xFF009BEB))
                    val items = List(2) { "Item $it" }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items.size) { index ->
                            Colecao(navController = navController,"Ditto","pop-menu-Personalizadas","entra-colecao")
                        }
                    }

                }
                POPUPmenu(navController = navController, title = name)
            }
            composable("pop-apagar"){
                Column(modifier = Modifier

                    .blur(10.dp)) {
                    TopbarPrincipal(navController = navController, "Coleções","Criar Coleção","pesquisar-colecao","criar_colecao")
                    MenuEscolha(navController = navController,Color(0xFF16B4FF),Color(0xFF009BEB))
                    val items = List(2) { "Item $it" }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items.size) { index ->
                            Colecao(navController = navController,"Ditto","pop-menu-Personalizadas","entra-colecao")
                        }
                    }

                }
                POPUP_apagar(navController = navController,"Tem a certeza que deseja apagar esta Coleção?","home")
            }
            composable("pop-editar"){
                ChamaEditarColecao(navController = navController)
            }
            composable("entra-colecao"){
                ChamaDentroColecao(navController = navController)
            }
            composable("entra-colecao/{name}") { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
                ChamaDentroColecao(navController=navController, title = name)
            }

            //Carta
            composable("retirar-carta"){
                Column(modifier = Modifier

                    .blur(10.dp)) {
                    ChamaDentroColecao(navController = navController)

                }
                POPUP_apagar(navController = navController,"Tem a certeza que deseja retirar esta carta?","entra-colecao")
            }
            composable("descricao-carta"){
                DescricaoCarta(navController = navController)
            }
            composable("adicionar-carta"){
                ChamaAdicionarCarta(navController = navController)
            }


            //Predefinido
            composable("home-predefinido") {
                Column {
                    TopbarPrincipal(navController = navController, "Séries","Adicionar Série","pesquisar-predefinido","adicionar-serie")
                    MenuEscolha(navController = navController,Color(0xFF009BEB),Color(0xFF16B4FF))
                    val items = List(2) { "Item $it" }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items.size) { index ->
                            Colecao(navController = navController,"Predefinido","pop-menu-Predefinido","entra-Predefinida")
                        }
                    }

                }
            }
            composable("pesquisar-predefinido") {
                ChamaPesquisarColecao(navController = navController,"Pesquisar Série","home-predefinido")
            }
            composable("adicionar-serie") {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ChamaPesquisarColecao(navController = navController, "Adicionar Série", "home-predefinido")

                    Spacer(modifier = Modifier.weight(1f))

                    BotaoMaiorQuadrado("Confirmar")
                }
            }
            composable("pop-menu-Predefinido/{name}") {backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
                Column(modifier = Modifier

                    .blur(10.dp)) {
                    TopbarPrincipal(navController = navController, "Séries","Adicionar Série","pesquisar-predefinido","adicionar-serie")
                    MenuEscolha(navController = navController,Color(0xFF16B4FF),Color(0xFF009BEB))
                    val items = List(2) { "Item $it" }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items.size) { index ->
                            Colecao(navController = navController,"Predefinido","pop-menu-Predefinido","entra-Predefinida")
                        }
                    }

                }
                POPUPmenu_Predefinido(navController = navController,title = name)
            }

            composable("pop-apagar-Prededinido"){
                Column(modifier = Modifier

                    .blur(10.dp)) {
                    TopbarPrincipal(navController = navController, "Séries","Adicionar Série","pesquisar-predefinido","adicionar-serie")
                    MenuEscolha(navController = navController,Color(0xFF16B4FF),Color(0xFF009BEB))
                    val items = List(2) { "Item $it" }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items.size) { index ->
                            Colecao(navController = navController,"Predefinido","pop-menu-Predefinido","entra-Predefinida")
                        }
                    }

                }
                POPUP_apagar(navController = navController,"Tem a certeza que deseja retirar esta Série?","home-predefinido")
            }
            composable("entra-Predefinida/{name}"){backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: "Nome Padrão"
                ChamaDentroColecaoPredefinida(navController = navController,title = name)
            }
        }


    }

}
