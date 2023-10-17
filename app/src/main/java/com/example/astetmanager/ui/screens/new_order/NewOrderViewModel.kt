package com.example.astetmanager.ui.screens.new_order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astetmanager.data.AppRepository
import com.example.astetmanager.data.database.entities.Order
import com.example.astetmanager.data.database.entities.User
import com.example.astetmanager.data.database.entities.enums.Counterparty
import com.example.astetmanager.data.database.entities.enums.PartTypeClass
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import com.example.astetmanager.data.database.entities.enums.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NewOrderViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewOrderUiState(price = 0))
    val uiState = _uiState.asStateFlow()

    fun setCounterparty(counterparty: Counterparty) {
        _uiState.update { it.copy(counterparty = counterparty) }
    }

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        _uiState.update { it.copy(paymentMethod = paymentMethod) }
    }

    fun setArticularText(articularText: String) {
        _uiState.update { it.copy(articularText = articularText) }.also { updateDifferences() }
    }

    private fun updateDifferences() {
        updatePillowcasesAmountDifference()
        updateSheetsAmountDifference()
        updateDuvetCoversAmountDifference()
    }

    private fun updatePillowcasesAmountDifference() {
        viewModelScope.launch {
            val currentUiState = _uiState.value

            repository.countPartTypesByArticularAndSizeAndClass(
                articular = currentUiState.articularText,
                partTypeSize = currentUiState.selectedComplectSize,
                partTypeClass = PartTypeClass.PILLOWCASE
            ).also { pillowcasesInStorage ->
                _uiState.update {
                    it.copy(
                        pillowcasesAmountDifference = if (pillowcasesInStorage < it.pillowcasesAmount) it.pillowcasesAmount - pillowcasesInStorage else 0
                    )
                }
            }

        }
    }


    private fun updateSheetsAmountDifference() {
        viewModelScope.launch {
            val currentUiState = _uiState.value

            repository.countPartTypesByArticularAndSizeAndClass(
                articular = currentUiState.articularText,
                partTypeSize = currentUiState.selectedComplectSize,
                partTypeClass = PartTypeClass.SHEET
            ).also { sheetsInStorage ->
                _uiState.update {
                    it.copy(
                        sheetsAmountDifference = if (sheetsInStorage < it.sheetsAmount) it.sheetsAmount - sheetsInStorage else 0
                    )
                }
            }

        }
    }

    private fun updateDuvetCoversAmountDifference() {
        viewModelScope.launch {
            val currentUiState = _uiState.value

            repository.countPartTypesByArticularAndSizeAndClass(
                articular = currentUiState.articularText,
                partTypeSize = currentUiState.selectedComplectSize,
                partTypeClass = PartTypeClass.DUVET_COVER
            ).also { duvetCoversInStorage ->
                _uiState.update {
                    it.copy(
                        duvetCoversAmountDifference = if (duvetCoversInStorage < it.duvetCoversAmount) it.duvetCoversAmount - duvetCoversInStorage else 0
                    )
                }
            }

        }
    }

    fun setSelectedComplectSize(complectSize: PartTypeSize) {
        _uiState.update { it.copy(selectedComplectSize = complectSize) }
    }

    fun addPillowCase() {
        _uiState.update { it.copy(pillowcasesAmount = it.pillowcasesAmount.inc()) }
        updatePillowcasesAmountDifference()
    }

    fun removePillowCase() {
        _uiState.update {
            it.copy(
                pillowcasesAmount = if (it.pillowcasesAmount == 0) 0 else it.pillowcasesAmount.dec()
            )
        }
        updatePillowcasesAmountDifference()
    }

    fun addSheet() {
        _uiState.update { it.copy(sheetsAmount = it.sheetsAmount.inc()) }.also {
            updateSheetsAmountDifference()
        }
    }

    fun removeSheet() {
        _uiState.update {
            it.copy(
                sheetsAmount = if (it.sheetsAmount == 0) 0 else it.sheetsAmount.dec()
            )
        }
        updateSheetsAmountDifference()
    }

    fun addDuvetCover() {
        _uiState.update { it.copy(duvetCoversAmount = it.duvetCoversAmount.inc()) }
        updateDuvetCoversAmountDifference()
    }

    fun removeDuvetCover() {
        _uiState.update {
            it.copy(
                duvetCoversAmount = if (it.duvetCoversAmount == 0) 0 else it.duvetCoversAmount.dec()
            )
        }
        updateDuvetCoversAmountDifference()
    }

    fun setPrice(priceAsString: String) {
        _uiState.update { it.copy(price = priceAsString.toIntOrNull() ?: 0) }
    }

    fun addNewOrder() {
        val currentUiState = _uiState.value

        viewModelScope.launch {

            val orderId = repository.addNewOrder(
                Order(
                    counterparty = currentUiState.counterparty,
                    paymentMethod = currentUiState.paymentMethod
                )
            )

            currentUiState.apply {
                Log.d(
                    "AAA",
                    "addNewOrder: differences: pc - $pillowcasesAmountDifference, s - $sheetsAmountDifference, dc - $duvetCoversAmountDifference"
                )
//                if (pillowcasesAmountDifference > 0) {
                repository.addTask(
                    orderId = orderId,
                    articular = articularText,
                    count = pillowcasesAmount,
                    partTypeClass = PartTypeClass.PILLOWCASE,
                    partTypeSize = selectedComplectSize
                )
//                }

//                if (sheetsAmountDifference > 0) {
                repository.addTask(
                    orderId = orderId,
                    articular = articularText,
                    count = sheetsAmount,
                    partTypeClass = PartTypeClass.SHEET,
                    partTypeSize = selectedComplectSize
                )
//                }

//                if (duvetCoversAmountDifference > 0) {
                repository.addTask(
                    orderId = orderId,
                    articular = articularText,
                    count = duvetCoversAmount,
                    partTypeClass = PartTypeClass.DUVET_COVER,
                    partTypeSize = selectedComplectSize
                )
//                }
            }
        }
    }
}

data class NewOrderUiState(
    val counterparty: Counterparty = Counterparty.OZON,
    val paymentMethod: PaymentMethod = PaymentMethod.CASH,
    val selectedUser: User? = null,
    val articularText: String = "",
    val selectedComplectSize: PartTypeSize = PartTypeSize.M,
    val pillowcasesAmount: Int = 0,
    val pillowcasesAmountDifference: Int = 0,
    val sheetsAmount: Int = 0,
    val sheetsAmountDifference: Int = 0,
    val duvetCoversAmount: Int = 0,
    val duvetCoversAmountDifference: Int = 0,
    val price: Int
)
