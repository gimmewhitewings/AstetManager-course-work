package com.example.astetmanager.ui.screens.storage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class StorageViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(StorageUiState())
    val uiState = _uiState.asStateFlow()
}

data class StorageUiState(
    val placeholder: String = ""
)
