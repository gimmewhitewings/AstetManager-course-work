package com.example.astetmanager.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithTasks(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val tasks: List<Task>
)
