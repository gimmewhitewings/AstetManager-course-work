package com.example.astetmanager.ui.screens.implementation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ImplementationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ImplementationUiState())
    val uiState = _uiState.asStateFlow()
}

data class ImplementationUiState(
    val placeholder: String = ""
)
