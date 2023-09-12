package com.example.astetmanager.ui.screens.application

import com.example.astetmanager.R

enum class PaymentMethod {
    CASH,
    CARD
}

fun PaymentMethod.getStringResourceId(): Int {
    return when (this) {
        PaymentMethod.CASH -> R.string.cash
        PaymentMethod.CARD -> R.string.card
    }
}
