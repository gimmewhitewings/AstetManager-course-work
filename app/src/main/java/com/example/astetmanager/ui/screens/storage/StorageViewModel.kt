package com.example.astetmanager.ui.screens.storage

import androidx.lifecycle.ViewModel
import com.example.astetmanager.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StorageUiState())
    val uiState = _uiState.asStateFlow()
}

data class StorageUiState(
    val placeholder: String = ""
)
