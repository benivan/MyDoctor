package com.example.mydoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydoctor.data.Medication
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedication(medications: List<Medication>)

    @Query("SELECT * FROM medication_table ORDER BY medicationId ASC")
    fun getMedication(): Flow<List<Medication>>

    @Query("SELECT * FROM medication_table WHERE diseaseId =:diseaseId")
    suspend fun getMedicationFromDiseaseId(diseaseId: Long): List<Medication>
}
