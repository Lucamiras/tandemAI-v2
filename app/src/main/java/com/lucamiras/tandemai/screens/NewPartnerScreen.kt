package com.lucamiras.tandemai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucamiras.tandemai.data.Language
import kotlin.contracts.SimpleEffect
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPartnerScreen(navController: NavController) {
    val languages = remember { Language.entries.toList() }
    val languageDropdownExpanded = remember { mutableStateOf(false) }
    val languageItemIndex = remember { mutableIntStateOf(0) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Create new partner")
                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text(
                text = "Specify language, name, personality and skill level to start talking to your new tandem partner.",
                modifier = Modifier
                    .padding(20.dp)
            )
            Box(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Row (
                    modifier = Modifier
                        .clickable { languageDropdownExpanded.value = true }
                        .background( Color.LightGray)
                        .padding(20.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = languages[languageItemIndex.intValue].name
                    )
                }
            }
            DropdownMenu(
                expanded = languageDropdownExpanded.value,
                onDismissRequest = { languageDropdownExpanded.value = false },
                offset = DpOffset(20.dp, 0.dp)
            ) {
                languages.forEachIndexed{ index, language ->
                    DropdownMenuItem(
                        text = { Text( text = language.name) },
                        onClick = {
                            languageDropdownExpanded.value = false
                            languageItemIndex.intValue = index
                        }
                    )
                }
            }
            Text(text="Placerholder for name")
        }
    }
}
