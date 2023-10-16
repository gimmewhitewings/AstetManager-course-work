package com.example.astetmanager.ui.screens.schedule

import androidx.lifecycle.ViewModel
import com.example.astetmanager.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()
}

data class ScheduleUiState(
    val placeholder: String = ""
)
