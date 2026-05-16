package com.example.reshme_namma_pride.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.reshme_namma_pride.data.local.entity.Batch
import com.example.reshme_namma_pride.data.repository.BatchRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BatchViewModel(private val repository: BatchRepository) : ViewModel() {

    val allBatches: StateFlow<List<Batch>> = repository.allBatches
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeBatch: StateFlow<Batch?> = repository.activeBatch
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun insertBatch(breed: String) {
        viewModelScope.launch {
            repository.insert(Batch(breed = breed, startDate = System.currentTimeMillis()))
        }
    }

    fun updateBatch(batch: Batch) {
        viewModelScope.launch {
            repository.update(batch)
        }
    }

    fun deleteBatch(batch: Batch) {
        viewModelScope.launch {
            repository.delete(batch)
        }
    }
}

class BatchViewModelFactory(private val repository: BatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BatchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BatchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
