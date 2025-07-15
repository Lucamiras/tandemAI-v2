package com.lucamiras.tandemai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lucamiras.tandemai.data.Language
import com.lucamiras.tandemai.data.local.Partner
import com.lucamiras.tandemai.viewModels.PartnerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPartnerScreen(navController: NavController, partnerViewModel: PartnerViewModel = hiltViewModel()) {
    val languages = remember { Language.entries.toList() }
    val languageDropdownExpanded = remember { mutableStateOf(false) }
    val languageItemIndex = remember { mutableIntStateOf(0) }
    val dpOffset: Dp = 20.dp
    var name by remember { mutableStateOf("")}

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
                    .padding(dpOffset)
            )
            Box(
                modifier = Modifier
                    .padding(dpOffset)
            ) {
                Row (
                    modifier = Modifier
                        .clickable { languageDropdownExpanded.value = true }
                        .background( Color.LightGray)
                        .padding(dpOffset)
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
                offset = DpOffset(dpOffset, 0.dp)
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
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name")},
                modifier = Modifier
                    .padding(dpOffset)
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier
                    .padding(dpOffset)
                    .fillMaxWidth(),
                onClick = {
                    if (nameValid(name)) {
                        partnerViewModel.addPartner(
                            Partner(
                                name = name.trim(),
                                language = languages[languageItemIndex.intValue]
                            )
                        )
                        navController.navigate(route = "partnerScreen")
                    }
                },
                enabled = nameValid(name)
            ) {
                Text("Create new partner")
            }
        }
    }
}

fun nameValid(name:String): Boolean {
    return name.isNotBlank() and (name.length < 25)
}

