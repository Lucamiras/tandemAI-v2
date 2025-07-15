package com.lucamiras.tandemai.di
import android.app.Application
import androidx.room.Room
import com.lucamiras.tandemai.data.local.PartnerDao
import com.lucamiras.tandemai.data.local.PartnerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesPartnerDatabase(app: Application): PartnerDatabase {
        return Room.databaseBuilder(
            app,
            PartnerDatabase::class.java,
            "partner_db"
        ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    @Singleton
    fun providesPartnerDao(database: PartnerDatabase): PartnerDao {
        return database.partnerDao()
    }
}