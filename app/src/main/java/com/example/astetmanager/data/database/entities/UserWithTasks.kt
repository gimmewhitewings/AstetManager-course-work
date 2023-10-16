package com.example.astetmanager.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTasks(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val tasks: List<Task>
)
