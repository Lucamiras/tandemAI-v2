package com.lucamiras.tandemai.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.lucamiras.tandemai.data.AppDatabase
import com.lucamiras.tandemai.data.DBPartner
import com.lucamiras.tandemai.data.Language
import com.lucamiras.tandemai.data.PartnerDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PartnerViewModel(context: Context) : ViewModel() {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "partners"
    ).fallbackToDestructiveMigration(false).build()
    private val partnerDao = db.partnerDao()
    private val _partners = MutableStateFlow<List<DBPartner>>(emptyList())
    val partners: StateFlow<List<DBPartner>> = _partners.asStateFlow()
    private var mutableItemsList: MutableList<DBPartner> = mutableListOf()
    init {
        getPartners()
    }
    fun getPartners() {
        viewModelScope.launch {
            try {
                val fetchedPartners = partnerDao.getAll()
                _partners.value = fetchedPartners
                mutableItemsList.clear()
                mutableItemsList.addAll(fetchedPartners)
            } catch (e: Exception) {
                _partners.value = emptyList()
                mutableItemsList.clear()
                Log.e("fetchedPartners", e.message.toString())
            }
        }
    }
    fun addOnePartner(partner:DBPartner) {
        viewModelScope.launch {
            try {
                partnerDao.insertAll(
                    partner
                )
            } catch (e: Exception) {
                Log.e("addOnePartner", e.message.toString())
            }
        }
    }
}