package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labs_table")
data class Lab(
    @PrimaryKey(autoGenerate = true)
    val labId: Long? = 0,
    val diseaseId: Long,
    val test: String,
    val report: String
)
