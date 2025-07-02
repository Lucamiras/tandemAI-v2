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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lucamiras.tandemai.data.AppDatabase
import com.lucamiras.tandemai.data.DBPartner

import com.lucamiras.tandemai.data.Language
import com.lucamiras.tandemai.data.PartnerDao
import com.lucamiras.tandemai.ui.elements.PartnerCard
import com.lucamiras.tandemai.ui.elements.PartnerObj
import com.lucamiras.tandemai.viewModels.PartnerViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartnerScreen(navController: NavController, partnerViewModel: PartnerViewModel) {

    val partnerList: State<List<DBPartner>> = partnerViewModel.partners.collectAsState()

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
                items(partnerList.value) { item ->
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
