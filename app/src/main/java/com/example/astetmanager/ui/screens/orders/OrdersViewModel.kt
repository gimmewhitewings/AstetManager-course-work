package com.example.astetmanager.ui.screens.orders

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class OrdersViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState = _uiState.asStateFlow()
}

data class OrdersUiState(
    val placeholder: String = ""
)
