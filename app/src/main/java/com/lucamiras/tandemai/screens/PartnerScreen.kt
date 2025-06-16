package com.lucamiras.tandemai.screens

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.lucamiras.tandemai.data.Language
import com.lucamiras.tandemai.ui.elements.PartnerCard
import com.lucamiras.tandemai.ui.elements.Partner


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartnerScreen(navController: NavController) {
    val partnerList = listOf(
        Partner(name = "John", language = Language.English),
        Partner(name = "Zsuzsa", language = Language.Hungarian),
        Partner(name = "Matthias", language = Language.German)
    )
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(text = "Partners")}
        ) }
    ) { innerPadding ->
        Column {
            LazyColumn (
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                items(partnerList) { item ->
                    PartnerCard(
                        partner = item,
                    )
                }
            }
            Button(
                onClick = { navController.navigate(route = "newPartnerScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            ) {
                Row {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new partner")
                    Text(text = "Add new partner")

                }
            }
        }
    }
}