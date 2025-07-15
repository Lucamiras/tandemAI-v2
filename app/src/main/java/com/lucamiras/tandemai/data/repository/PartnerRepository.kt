package com.lucamiras.tandemai.data.repository

import com.lucamiras.tandemai.data.local.Partner
import com.lucamiras.tandemai.data.local.PartnerDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartnerRepository @Inject constructor(private val partnerDao: PartnerDao) {

    fun getAllPartner(): Flow<List<Partner>> = partnerDao.getAllPartners()

    suspend fun insert(partner: Partner) {
        partnerDao.insertAll(partner)
    }

    suspend fun delete(partner: Partner) {
        partnerDao.delete(partner)
    }
}