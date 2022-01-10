package com.example.mydoctor.repository

import android.util.Log
import com.example.mydoctor.data.Disease
import com.example.mydoctor.data.Lab
import com.example.mydoctor.data.Medication
import com.example.mydoctor.data.dao.DiseaseDao
import com.example.mydoctor.data.dao.LabsDao
import com.example.mydoctor.data.dao.MedicationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val diseaseDao: DiseaseDao,
    private val medicationDao: MedicationDao,
    private val labsDao: LabsDao
) {
    suspend fun getAllDisease(): List<Disease> {
        Log.d(TAG, "getAllDisease: FunctionCalled")
        return diseaseDao.getDiseases()
    }

    suspend fun insertDisease(disease: Disease) {
        diseaseDao.insertDisease(disease)
    }

    suspend fun getLabsFromDiseaseId(diseaseId: Long) :List<Lab>{
        return labsDao.getLabsFromDiseaseId(diseaseId)
    }

    suspend fun getMedicationFromDiseaseId(diseaseId: Long) :List<Medication>{
        return medicationDao.getMedicationFromDiseaseId(diseaseId)
    }

    companion object {
        private const val TAG = "RoomRepository"
    }
}