package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.database.entities.enums.Counterparty
import com.example.astetmanager.data.database.entities.enums.OrderStatus
import com.example.astetmanager.data.database.entities.enums.PaymentMethod
import java.util.Date

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int? = null,
    val orderStatus: OrderStatus = OrderStatus.IN_PROGRESS,
    val counterparty: Counterparty,
    val paymentMethod: PaymentMethod,
    //val priceRub: Int = 0,
    val creationDate: Long = Date().time
)

