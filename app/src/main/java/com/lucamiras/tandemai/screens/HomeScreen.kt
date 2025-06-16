package com.lucamiras.tandemai.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucamiras.tandemai.ui.elements.Greeting
import com.lucamiras.tandemai.ui.elements.GridCard
import com.lucamiras.tandemai.ui.elements.GridCardData


@Composable
fun HomeScreen(navController: NavController) {
    val cardItems = listOf<GridCardData>(
        GridCardData("New chat", Icons.Filled.AddCircle, "testScreen"),
        GridCardData("Existing chats", Icons.Filled.Email, "testScreen"),
        GridCardData("Partners", Icons.Filled.Face, "partnerScreen"),
        GridCardData("Profile", Icons.Filled.AccountCircle, "profileScreen"),
    )
    Scaffold (
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Made with <3 by Doge for Panda",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Greeting(name = "User")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(cardItems) { item ->
                    GridCard(item = item, onClick = {
                        navController.navigate(item.route)
                    })
                }
            }
        }
    }
}
