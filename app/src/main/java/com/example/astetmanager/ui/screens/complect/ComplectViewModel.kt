package com.example.astetmanager.ui.screens.complect

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ComplectViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ComplectUiState())
    val uiState: StateFlow<ComplectUiState> = _uiState.asStateFlow()

    fun setComplectName(complectName: String) = _uiState.update {
        it.copy(complectName = complectName)
    }

    fun clearComplectName() = setComplectName("")

    fun setComplectVendorCode(complectVendorCode: String) = _uiState.update {
        it.copy(complectVendorCode = complectVendorCode)
    }

    fun setSelectedComplectSize(complectSize: ComplectSize) = _uiState.update {
        it.copy(selectedComplectSize = complectSize)
    }

    fun addPillowCase() = _uiState.update {
        it.copy(pillowcasesAmount = it.pillowcasesAmount + 1)
    }

    fun removePillowCase() = _uiState.update {
        it.copy(pillowcasesAmount = if (it.pillowcasesAmount == 0) 0 else it.pillowcasesAmount - 1)
    }

    fun addSheet() = _uiState.update {
        it.copy(sheetsAmount = it.sheetsAmount + 1)
    }

    fun removeSheet() = _uiState.update {
        it.copy(sheetsAmount = if (it.sheetsAmount == 0) 0 else it.sheetsAmount - 1)
    }

    fun addDuvetCover() = _uiState.update {
        it.copy(duvetCoversAmount = it.duvetCoversAmount + 1)
    }

    fun removeDuvetCover() = _uiState.update {
        it.copy(duvetCoversAmount = if (it.duvetCoversAmount == 0) 0 else it.duvetCoversAmount - 1)
    }

    fun saveComplectInfo() = {}
}

data class ComplectUiState(
    val complectName: String = "",
    val complectVendorCode: String = "",
    val selectedComplectSize: ComplectSize = ComplectSize.M,
    val pillowcasesAmount: Int = 0,
    val sheetsAmount: Int = 0,
    val duvetCoversAmount: Int = 0,
)
