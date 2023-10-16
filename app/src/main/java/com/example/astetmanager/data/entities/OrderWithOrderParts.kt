package com.example.astetmanager.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithOrderParts(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val orderParts: List<OrderPart>
)
