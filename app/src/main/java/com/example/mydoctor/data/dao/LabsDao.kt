package com.example.mydoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydoctor.data.Lab
import kotlinx.coroutines.flow.Flow

@Dao
interface LabsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLabs(labs: List<Lab>)

    @Query("SELECT * FROM labs_table ORDER BY labId ASC")
    fun getLabs(): Flow<List<Lab>>

    @Query("SELECT * FROM labs_table WHERE diseaseId =:diseaseId")
    suspend fun getLabsFromDiseaseId(diseaseId: Long): List<Lab>
}