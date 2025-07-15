package com.lucamiras.tandemai.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucamiras.tandemai.data.local.Partner
import com.lucamiras.tandemai.data.repository.PartnerRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class PartnerViewModel(private val partnerRepository: PartnerRepository) : ViewModel() {
    val allPartners: StateFlow<List<Partner>> = partnerRepository.getAllPartner()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun addPartner(partner: Partner) {
        viewModelScope.launch {
            partnerRepository.insert(partner)
        }
    }

    fun deletePartner(partner: Partner) {
        viewModelScope.launch {
            partnerRepository.delete(partner)
        }
    }
}