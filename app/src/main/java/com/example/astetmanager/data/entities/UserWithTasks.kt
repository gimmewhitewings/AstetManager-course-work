package com.example.astetmanager.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTasks(
    @Embedded
    val userId: Int,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val tasks: List<Task>
)
