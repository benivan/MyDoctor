package com.example.mydoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydoctor.data.Disease
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisease(disease: Disease): Long

    @Query("SELECT * FROM disease_table ORDER BY diseaseId DESC")
    suspend fun getDiseases():List<Disease>

}