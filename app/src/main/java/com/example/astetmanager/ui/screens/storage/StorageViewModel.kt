package com.example.astetmanager.ui.screens.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astetmanager.data.AppRepository
import com.example.astetmanager.data.database.entities.PartType
import com.example.astetmanager.data.database.entities.enums.PartTypeClass
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StorageUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPartTypes().collect { partTypesList ->
                setDisplayablePartTypesList(partTypesList)
            }
        }
    }

    fun addPartType() {
        viewModelScope.launch {
            _uiState.value.apply {
                if (articularText.isNotEmpty() && addingPartTypeClassAmount != 0) {
                    repository.addPartType(
                        PartType(
                            partTypeClass = selectedAddingPartTypeClass,
                            articular = articularText,
                            count = addingPartTypeClassAmount,
                            partTypeSize = selectedPartTypeSize
                        )
                    )
                }
            }
        }
    }

    fun addComplect() {
        viewModelScope.launch {
            _uiState.value.apply {
                if (pillowcasesAmount > 0) {
                    repository.addPartType(
                        PartType(
                            partTypeClass = PartTypeClass.PILLOWCASE,
                            articular = articularText,
                            count = pillowcasesAmount,
                            partTypeSize = selectedPartTypeSize
                        )
                    )
                }

                if (sheetsAmount > 0) {
                    repository.addPartType(
                        PartType(
                            partTypeClass = PartTypeClass.SHEET,
                            articular = articularText,
                            count = sheetsAmount,
                            partTypeSize = selectedPartTypeSize
                        )
                    )
                }

                if (duvetCoversAmount > 0) {
                    repository.addPartType(
                        PartType(
                            partTypeClass = PartTypeClass.DUVET_COVER,
                            articular = articularText,
                            count = duvetCoversAmount,
                            partTypeSize = selectedPartTypeSize
                        )
                    )
                }
            }
        }
    }

    private fun setDisplayablePartTypesList(partTypesList: List<PartType>) {
        _uiState.update {
            it.copy(
                displayablePartTypesList = partTypesList
            )
        }
    }

    fun setArticularText(articularText: String) {
        _uiState.update { it.copy(articularText = articularText) }
    }


    fun increaseAddingPartTypeClassAmount() {
        _uiState.update { it.copy(addingPartTypeClassAmount = it.addingPartTypeClassAmount + 1) }
    }

    fun decreaseAddingPartTypeClassAmount() {
        _uiState.update {
            it.copy(
                addingPartTypeClassAmount = if (it.addingPartTypeClassAmount == 0) 0 else it.addingPartTypeClassAmount - 1
            )
        }
    }

    fun addPillowCase() {
        _uiState.update { it.copy(pillowcasesAmount = it.pillowcasesAmount + 1) }
    }

    fun removePillowCase() {
        _uiState.update {
            it.copy(
                pillowcasesAmount = if (it.pillowcasesAmount == 0) 0 else it.pillowcasesAmount - 1
            )
        }
    }

    fun addSheet() {
        _uiState.update { it.copy(sheetsAmount = it.sheetsAmount + 1) }
    }

    fun removeSheet() {
        _uiState.update {
            it.copy(
                sheetsAmount = if (it.sheetsAmount == 0) 0 else it.sheetsAmount - 1
            )
        }
    }

    fun addDuvetCover() {
        _uiState.update { it.copy(duvetCoversAmount = it.duvetCoversAmount + 1) }
    }

    fun removeDuvetCover() {
        _uiState.update {
            it.copy(
                duvetCoversAmount = if (it.duvetCoversAmount == 0) 0 else it.duvetCoversAmount - 1
            )
        }
    }

    fun selectAddingPartTypeClass(partTypeClass: PartTypeClass) {
        _uiState.update {
            it.copy(
                selectedAddingPartTypeClass = partTypeClass
            )
        }
    }

    fun setSelectedPartTypeSize(partTypeSize: PartTypeSize) = _uiState.update {
        it.copy(selectedPartTypeSize = partTypeSize)
    }

    fun setPrice(priceAsString: String) {
        _uiState.update { it.copy(price = priceAsString.toIntOrNull() ?: 0) }
    }
}

data class StorageUiState(
    val displayablePartTypesList: List<PartType> = emptyList(),
    val price: Int = 0,
    val selectedAddingPartTypeClass: PartTypeClass = PartTypeClass.SHEET,
    val addingPartTypeClassAmount: Int = 0,
    val articularText: String = "",
    val pillowcasesAmount: Int = 0,
    val sheetsAmount: Int = 0,
    val duvetCoversAmount: Int = 0,
    val selectedPartTypeSize: PartTypeSize = PartTypeSize.M,
)
