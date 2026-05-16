package com.example.reshme_namma_pride.data.local.dao

import androidx.room.*
import com.example.reshme_namma_pride.data.local.entity.Batch
import kotlinx.coroutines.flow.Flow

@Dao
interface BatchDao {
    @Query("SELECT * FROM batches ORDER BY startDate DESC")
    fun getAllBatches(): Flow<List<Batch>>

    @Query("SELECT * FROM batches WHERE status = 'Active' LIMIT 1")
    fun getActiveBatch(): Flow<Batch?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatch(batch: Batch)

    @Update
    suspend fun updateBatch(batch: Batch)

    @Delete
    suspend fun deleteBatch(batch: Batch)
}
