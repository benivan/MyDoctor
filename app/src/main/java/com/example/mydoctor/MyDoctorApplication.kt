package com.example.mydoctor

import android.app.Application
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.mydoctor.util.PROBLEM_DATA_FILENAME
import com.example.mydoctor.workers.PopulateDatabaseWorker
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyDoctorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences =
            applicationContext.getSharedPreferences("doctor", Context.MODE_PRIVATE)
        val isDatabaseSeeded = sharedPreferences.getBoolean("databaseSeeded", false)
        if (!isDatabaseSeeded) {
            val request =
                OneTimeWorkRequestBuilder<PopulateDatabaseWorker>()
                    .setInputData(workDataOf(PopulateDatabaseWorker.KEY_FILENAME to PROBLEM_DATA_FILENAME))
                    .build()
            WorkManager.getInstance(this).enqueue(request)
        }

    }
}