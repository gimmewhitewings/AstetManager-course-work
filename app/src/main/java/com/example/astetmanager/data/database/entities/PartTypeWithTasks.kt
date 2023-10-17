package com.example.astetmanager.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PartTypeWithTasks(
    @Embedded val partType: PartType,
    @Relation(
        parentColumn = "partTypeId",
        entityColumn = "partTypeId"
    )
    val tasks: List<Task>
)
