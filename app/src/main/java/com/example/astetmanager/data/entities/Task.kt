package com.example.astetmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val orderId: Int,
    val partTypeId: Int,
    val userId: Int,
    val isCompleted: Boolean = false,
    val creationDate: Timestamp
)
