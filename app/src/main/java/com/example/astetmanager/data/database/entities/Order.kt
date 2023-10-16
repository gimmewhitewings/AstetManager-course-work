package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.database.entities.enums.OrderStatus
import java.util.Date

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int,
    val orderStatus: OrderStatus,
    val priceRub: Int = 0,
    val creationDate: Long = Date().time
)

