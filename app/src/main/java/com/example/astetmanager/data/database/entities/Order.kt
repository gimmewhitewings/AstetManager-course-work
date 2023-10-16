package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.database.entities.enums.OrderStatus

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int,
    val orderStatus: OrderStatus,
    val priceRub: Int,
    val creationDate: Long
)

