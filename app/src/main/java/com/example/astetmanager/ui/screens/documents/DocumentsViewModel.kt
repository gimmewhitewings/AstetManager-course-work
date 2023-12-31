package com.example.astetmanager.ui.screens.documents

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DocumentsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DocumentsUiState())
    val uiState = _uiState.asStateFlow()
}

data class DocumentsUiState(
    val placeholder: String = ""
)
