package com.example.tpsi_pokemon.components


import android.os.Bundle
import com.example.tpsi_pokemon.R
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpsi_pokemon.ui.theme.BlueP
import com.example.tpsi_pokemon.ui.theme.BlueB
import com.example.tpsi_pokemon.ui.theme.TPSI_PokemonTheme


@Composable
fun UserListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(text = "ImBouncer", onClick = { navController.popBackStack() })
        UserList(navController = navController) // Passando o navController para UserList
    }
}

@Composable
fun UserList(navController: NavController) {
    val users = listOf(
        User("@ImBouncer", "João Rodrigues", "28/01/2018", R.drawable.img_4954),
        User("@ImBouncer7", "João Neves Rodrigues", "03/09/2023", R.drawable.img_4954),
        User("@ImBouncer883", "João Rodrigues", "23/11/2022", R.drawable.img_4954),
        User("@ImBouncer6", "João Neves Rodrigues", "04/08/2021", R.drawable.img_4954)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        users.forEach { user ->
            UserCard(user, onClick = { navController.navigate("home") })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun UserCard(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2196F3), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clickable { onClick() },  // Aplicando o clique diretamente no Row
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = user.imageRes),
            contentDescription = "User Image",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = user.username, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = user.name, color = Color.White, fontSize = 14.sp)
            Text(text = user.date, color = Color.White, fontSize = 12.sp)
        }
    }
}

data class User(val username: String, val name: String, val date: String, val imageRes: Int)