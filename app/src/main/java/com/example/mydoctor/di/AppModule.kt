package com.example.mydoctor.di

import android.content.Context
import com.example.mydoctor.data.AppDatabase
import com.example.mydoctor.data.dao.DiseaseDao
import com.example.mydoctor.data.dao.LabsDao
import com.example.mydoctor.data.dao.MedicationDao
import com.example.mydoctor.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDiseaseDao(appDatabase: AppDatabase): DiseaseDao {
        return appDatabase.diseaseDao()
    }

    @Provides
    @Singleton
    fun provideMedicationDao(appDatabase: AppDatabase): MedicationDao {
        return appDatabase.medicationDao()
    }

    @Provides
    @Singleton
    fun provideLabsDao(appDatabase: AppDatabase): LabsDao {
        return appDatabase.labsDao()
    }

    @Provides
    @Singleton
    fun providesRepository(
        diseaseDao: DiseaseDao,
        medicationDao: MedicationDao,
        labsDao: LabsDao
    ): RoomRepository {
        return RoomRepository(diseaseDao, medicationDao, labsDao)
    }

}