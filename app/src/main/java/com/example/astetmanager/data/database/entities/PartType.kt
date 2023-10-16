package com.example.astetmanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.database.entities.enums.PartTypeClass
import com.example.astetmanager.data.database.entities.enums.PartTypeSize

@Entity
data class PartType(
    @PrimaryKey(autoGenerate = true)
    val partTypeId: Int,
    val partTypeClass: PartTypeClass,
    val articular: String,
    val partTypeSize: PartTypeSize,
    val costRub: Int = 0,
    val count: Int = 0
)

