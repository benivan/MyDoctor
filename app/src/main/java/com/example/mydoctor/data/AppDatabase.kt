package com.example.mydoctor.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydoctor.data.dao.DiseaseDao
import com.example.mydoctor.data.dao.LabsDao
import com.example.mydoctor.data.dao.MedicationDao
import com.example.mydoctor.util.DATABASE_NAME


@Database(
    entities = [Disease::class, Medication::class, Lab::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diseaseDao(): DiseaseDao
    abstract fun medicationDao(): MedicationDao
    abstract fun labsDao(): LabsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .build().also {
                        INSTANCE = it
                    }
            }
        }

        private const val TAG = "AppDatabase"
    }
}