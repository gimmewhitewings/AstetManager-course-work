package com.example.astetmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.entities.enums.OrderStatus
import java.sql.Timestamp

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int,
    val orderStatus: OrderStatus,
    val priceRub: Int,
    val creationDate: Timestamp
)

