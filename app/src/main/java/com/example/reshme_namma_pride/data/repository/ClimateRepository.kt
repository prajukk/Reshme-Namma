package com.example.reshme_namma_pride.data.repository

import com.example.reshme_namma_pride.data.local.dao.ClimateDao
import com.example.reshme_namma_pride.data.local.entity.ClimateRecord
import kotlinx.coroutines.flow.Flow

class ClimateRepository(private val climateDao: ClimateDao) {
    val latestRecord: Flow<ClimateRecord?> = climateDao.getLatestRecord()
    val allRecords: Flow<List<ClimateRecord>> = climateDao.getAllRecords()

    suspend fun insert(record: ClimateRecord) {
        climateDao.insertRecord(record)
    }

    suspend fun deleteAll() {
        climateDao.deleteAll()
    }
}
