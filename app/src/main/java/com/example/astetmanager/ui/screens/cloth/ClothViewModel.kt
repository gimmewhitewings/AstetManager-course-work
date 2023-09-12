package com.example.astetmanager.ui.screens.cloth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astetmanager.data.ClothEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ClothViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ClothUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    clothEntities = listOf(
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1),
                        ClothEntity(1, "test", 1)
                    )
                )
            }
        }
    }

    fun setClothName(clothName: String) = _uiState.update {
        it.copy(clothName = clothName)
    }

    fun clearClothName() = setClothName("")
    fun saveCloth() {
        TODO("Not yet implemented")
    }

    fun addNewClothPart() {
        TODO("Not yet implemented")
    }

    fun setNewPartInMetersText(newPartInMetersText: String) {
        _uiState.update {
            it.copy(
                newPartInMetersText = newPartInMetersText
            )
        }
    }
}

data class ClothUiState(
    val clothName: String = "",
    val newPartInMetersText: String = "",
    val clothEntities: List<ClothEntity> = emptyList()
)
