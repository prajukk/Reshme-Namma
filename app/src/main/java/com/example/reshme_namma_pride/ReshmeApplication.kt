package com.example.reshme_namma_pride

import android.app.Application
import com.example.reshme_namma_pride.data.local.AppDatabase
import com.example.reshme_namma_pride.data.repository.BatchRepository

class ReshmeApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BatchRepository(database.batchDao()) }
}
