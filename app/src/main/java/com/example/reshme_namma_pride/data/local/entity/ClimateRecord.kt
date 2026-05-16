package com.example.reshme_namma_pride.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "climate_records")
data class ClimateRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val temperature: Float,
    val humidity: Float,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String,
    val advice: String
)
