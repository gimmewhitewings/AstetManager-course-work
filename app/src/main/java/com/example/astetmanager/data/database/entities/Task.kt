package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val orderId: Int,
    val partTypeId: Int,
    val userId: Int? = null,
    val isCompleted: Boolean = false,
    val creationDate: Long = Date().time
)
