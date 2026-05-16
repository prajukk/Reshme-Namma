package com.example.reshme_namma_pride.data.repository

import com.example.reshme_namma_pride.data.local.dao.BatchDao
import com.example.reshme_namma_pride.data.local.entity.Batch
import kotlinx.coroutines.flow.Flow

class BatchRepository(private val batchDao: BatchDao) {
    val allBatches: Flow<List<Batch>> = batchDao.getAllBatches()
    val activeBatch: Flow<Batch?> = batchDao.getActiveBatch()

    suspend fun insert(batch: Batch) {
        batchDao.insertBatch(batch)
    }

    suspend fun update(batch: Batch) {
        batchDao.updateBatch(batch)
    }

    suspend fun delete(batch: Batch) {
        batchDao.deleteBatch(batch)
    }
}
