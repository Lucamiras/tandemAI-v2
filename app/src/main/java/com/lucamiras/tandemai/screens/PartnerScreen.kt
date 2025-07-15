package com.lucamiras.tandemai.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.lucamiras.tandemai.data.local.Partner
import com.lucamiras.tandemai.ui.elements.PartnerCard
import com.lucamiras.tandemai.viewModels.PartnerViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartnerScreen(navController: NavController, partnerViewModel: PartnerViewModel) {

    val partners by partnerViewModel.allPartners.collectAsStateWithLifecycle()

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
                items(partners) { item ->
                    PartnerCard(
                        partner = item,
                        partnerViewModel= partnerViewModel
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
