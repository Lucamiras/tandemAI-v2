package com.lucamiras.tandemai.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.lucamiras.tandemai.ui.elements.PartnerObj

@Entity
data class DBPartner(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "language") val language: Language
)

@Dao
interface PartnerDao {
    @Query("SELECT * FROM dbpartner")
    suspend fun getAll(): List<DBPartner>

    @Query("SELECT COUNT(uid) FROM dbpartner")
    suspend fun countAll(): Int

    @Insert
    suspend fun insertAll(vararg partner: DBPartner)

    @Delete
    suspend fun delete(partner: DBPartner)
}

@Database(entities = [DBPartner::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun partnerDao(): PartnerDao
}


