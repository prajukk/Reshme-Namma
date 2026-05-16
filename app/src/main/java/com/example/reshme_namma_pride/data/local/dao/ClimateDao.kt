package com.example.reshme_namma_pride.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reshme_namma_pride.data.local.entity.ClimateRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface ClimateDao {
    @Query("SELECT * FROM climate_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<ClimateRecord>>

    @Query("SELECT * FROM climate_records ORDER BY timestamp DESC LIMIT 1")
    fun getLatestRecord(): Flow<ClimateRecord?>

    @Insert
    suspend fun insertRecord(record: ClimateRecord)

    @Query("DELETE FROM climate_records")
    suspend fun deleteAll()
}
