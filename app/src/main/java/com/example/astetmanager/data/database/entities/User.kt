package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.database.entities.enums.UserRole

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val password: String,
    val username: String,
    val displayName: String,
    val userRole: UserRole
)

