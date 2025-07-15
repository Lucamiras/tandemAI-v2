package com.lucamiras.tandemai.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Dao
interface PartnerDao {
    @Query("SELECT * FROM partner")
    fun getAllPartners(): Flow<List<Partner>>

    @Query("SELECT COUNT(uid) FROM Partner")
    fun countAll(): Flow<Int>

    @Insert
    suspend fun insertAll(vararg partner: Partner)

    @Delete
    suspend fun delete(partner: Partner)
}