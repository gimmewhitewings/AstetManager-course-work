package com.example.astetmanager.ui.screens.application

import androidx.lifecycle.ViewModel
import com.example.astetmanager.data.database.entities.enums.Counterparty
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import com.example.astetmanager.data.database.entities.enums.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ApplicationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ApplicationUiState(price = 0))
    val uiState = _uiState.asStateFlow()

    fun setCounterparty(counterparty: Counterparty) {
        _uiState.update { it.copy(counterparty = counterparty) }
    }

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        _uiState.update { it.copy(paymentMethod = paymentMethod) }
    }

    fun setDesignText(designText: String) {
        _uiState.update { it.copy(designText = designText) }
    }

    fun setSelectedComplectSize(complectSize: PartTypeSize) {
        _uiState.update { it.copy(selectedComplectSize = complectSize) }
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

    fun setPrice(priceAsString: String) {
        _uiState.update { it.copy(price = priceAsString.toIntOrNull() ?: 0) }
    }
}

data class ApplicationUiState(
    val placeholder: String = "",
    val counterparty: Counterparty = Counterparty.OZON,
    val paymentMethod: PaymentMethod = PaymentMethod.CASH,
    val designText: String = "",
    val selectedComplectSize: PartTypeSize = PartTypeSize.M,
    val pillowcasesAmount: Int = 0,
    val sheetsAmount: Int = 0,
    val duvetCoversAmount: Int = 0,
    val price: Int
)
