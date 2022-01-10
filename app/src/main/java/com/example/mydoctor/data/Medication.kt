package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "medication_table"
)
data class Medication(
    @PrimaryKey(autoGenerate = true)
    val medicationId: Long? = 0,
    val diseaseId: Long,
    val name: String,
    val dose: String,
    val strength: String
)
