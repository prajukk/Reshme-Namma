package com.example.reshme_namma_pride.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.reshme_namma_pride.data.local.entity.ClimateRecord
import com.example.reshme_namma_pride.data.repository.ClimateRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClimateViewModel(private val repository: ClimateRepository) : ViewModel() {

    val latestRecord: StateFlow<ClimateRecord?> = repository.latestRecord
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val allRecords: StateFlow<List<ClimateRecord>> = repository.allRecords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertRecord(temp: Float, hum: Float, status: String, advice: String) {
        viewModelScope.launch {
            repository.insert(
                ClimateRecord(
                    temperature = temp,
                    humidity = hum,
                    status = status,
                    advice = advice
                )
            )
        }
    }
}

class ClimateViewModelFactory(private val repository: ClimateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClimateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
