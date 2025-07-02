package com.lucamiras.tandemai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lucamiras.tandemai.data.DBPartner
import com.lucamiras.tandemai.data.Language
import com.lucamiras.tandemai.viewModels.PartnerViewModel
import kotlin.contracts.SimpleEffect
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPartnerScreen(navController: NavController, partnerViewModel: PartnerViewModel) {
    val languages = remember { Language.entries.toList() }
    val languageDropdownExpanded = remember { mutableStateOf(false) }
    val languageItemIndex = remember { mutableIntStateOf(0) }
    val dpOffset: Dp = 20.dp
    var firstName by remember { mutableStateOf("")}
    var lastName by remember { mutableStateOf("")}

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
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name")},
                modifier = Modifier
                    .padding(dpOffset)
                    .fillMaxWidth()
            )
            TextField(
                value = lastName,
                onValueChange = { lastName = it},
                label = { Text("Last name")},
                modifier = Modifier
                    .padding(dpOffset)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    if (dataFilledOut(firstName, lastName)) {
                        partnerViewModel.addOnePartner(
                            DBPartner(
                                firstName = firstName,
                                lastName = lastName,
                                language = languages[languageItemIndex.intValue]
                            )
                        )
                        partnerViewModel.getPartners()
                        navController.navigate(route = "partnerScreen")
                    }
                },
                enabled = dataFilledOut(firstName, lastName)
            ) {
                Text("Create new partner")
            }
        }
    }
}

fun dataFilledOut(firstName:String, lastName:String): Boolean {
    return (firstName.isNotBlank() and lastName.isNotBlank())
}
