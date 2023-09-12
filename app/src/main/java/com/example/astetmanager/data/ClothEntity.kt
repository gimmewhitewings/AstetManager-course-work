package com.example.astetmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cloth")
data class ClothEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val lengthInMeters: Int
)
