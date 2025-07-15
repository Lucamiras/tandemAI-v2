package com.lucamiras.tandemai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Partner::class], version = 4)
abstract class PartnerDatabase : RoomDatabase() {
    abstract fun partnerDao(): PartnerDao
}