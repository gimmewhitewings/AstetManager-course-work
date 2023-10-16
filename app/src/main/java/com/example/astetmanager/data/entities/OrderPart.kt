package com.example.astetmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class OrderPart(
    @PrimaryKey(autoGenerate = true)
    val orderPartId: Int,
    val orderId: Int,
    val partTypeId: Int,
    val count: Int
)
