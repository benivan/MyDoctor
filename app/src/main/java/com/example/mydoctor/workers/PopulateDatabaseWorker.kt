package com.example.mydoctor.workers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mydoctor.data.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopulateDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val sharedPreferences =
                applicationContext.getSharedPreferences("doctor", Context.MODE_PRIVATE)
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val diseaseType = object : TypeToken<List<JsonDisease>>() {}.type
                        val diseaseList: List<JsonDisease> =
                            Gson().fromJson(jsonReader, diseaseType)

                        val database = AppDatabase.getInstance(applicationContext)
                        diseaseList.forEach { disease ->
                            val diseaseId =
                                database.diseaseDao().insertDisease(Disease(name = disease.name))

                            database.medicationDao().insertMedication(disease.medications.map {
                                Medication(
                                    null,
                                    diseaseId = diseaseId,
                                    name = it.name,
                                    dose = it.dose,
                                    strength = it.strength
                                )
                            }.toList())

                            database.labsDao().insertLabs(disease.labs.map {
                                Lab(
                                    null,
                                    diseaseId = diseaseId,
                                    test = it.test,
                                    report = it.report
                                )
                            }.toList())

                        }

                        sharedPreferences.edit {
                            putBoolean("databaseSeeded", true)
                        }

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error populating database -no valid filename")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error populating data", e)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "PopulateDatabaseWorker"
        const val KEY_FILENAME = "PROBLEM_DATA_FILENAME"
    }
}



