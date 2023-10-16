package com.example.astetmanager.data.database.entities

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
