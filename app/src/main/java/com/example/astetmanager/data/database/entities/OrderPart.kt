package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderPart(
    @PrimaryKey(autoGenerate = true)
    val orderPartId: Int? = null,
    val orderId: Int,
    val partTypeId: Int,
    val count: Int
)
