package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "disease_table")
data class Disease(
    @PrimaryKey(autoGenerate = true)
    val diseaseId: Long = 0L,
    val name: String,
)
