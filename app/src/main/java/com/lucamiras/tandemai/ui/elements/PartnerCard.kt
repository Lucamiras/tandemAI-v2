package com.lucamiras.tandemai.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lucamiras.tandemai.data.local.Partner
import com.lucamiras.tandemai.data.Language
import com.lucamiras.tandemai.viewModels.PartnerViewModel


data class PartnerObj(
    val name: String,
    val language: Language,
)

@Composable
fun PartnerCard(
    partner: Partner,
    partnerViewModel: PartnerViewModel
) {
    Card(
        shape = CardDefaults.shape,
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(15.dp)) {
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .background(Color.LightGray)
                    .padding(20.dp)
            )
            Column (
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                Text(partner.firstName, fontWeight = FontWeight.Bold)
                Text(partner.language.name, fontWeight = FontWeight.Normal)
            }
            Column (
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Button (
                    onClick = { partnerViewModel.deletePartner(partner) }
                ) {
                    Text("Delete")
                }
            }
        }
    }
}

