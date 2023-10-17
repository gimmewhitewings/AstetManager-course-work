package com.example.astetmanager.ui.screens.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astetmanager.data.AppRepository
import com.example.astetmanager.data.database.entities.Order
import com.example.astetmanager.data.database.entities.OrderWithOrderParts
import com.example.astetmanager.data.database.entities.enums.Counterparty
import com.example.astetmanager.data.database.entities.enums.OrderStatus
import com.example.astetmanager.data.database.entities.enums.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getOrdersWithOrderParts().collect { ordersWithOrderParts ->
                _uiState.update { ordersUiState ->
                    ordersUiState.copy(
                        orderUiStates = ordersWithOrderParts.map {
                            val orderStatus = it.order.orderStatus
                            val counterparty = it.order.counterparty
                            val paymentMethod = it.order.paymentMethod
                            val tasksCount = repository.countTasksForOrderById(it.order.orderId!!)
                            val articular =
                                it.orderParts.firstOrNull()
                                    ?.let { orderPart -> repository.getPartTypeArticularById(orderPart.partTypeId) }
                            return@map OrderUiState(
                                tasksCount = tasksCount,
                                status = orderStatus,
                                counterparty = counterparty,
                                paymentMethod = paymentMethod,
                                articular = articular
                            )
                        }
                    )
                }
            }
        }
    }
}

data class OrdersUiState(
    val orderUiStates: List<OrderUiState> = emptyList()
)

data class OrderUiState(
    val tasksCount: Int? = null,
    val pillowcasesCount: Int? = null,
    val sheetsCount: Int? = null,
    val duvetCoversCount: Int? = null,
    val counterparty: Counterparty,
    val paymentMethod: PaymentMethod,
    val articular: String?,
    val status: OrderStatus = OrderStatus.IN_PROGRESS
)
