package com.example.reshme_namma_pride.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "batches")
data class Batch(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val breed: String,
    val startDate: Long,
    val currentInstar: String = "1st Instar",
    val status: String = "Active"
)
