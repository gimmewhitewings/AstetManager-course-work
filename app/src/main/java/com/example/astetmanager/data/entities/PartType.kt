package com.example.astetmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astetmanager.data.entities.enums.PartTypeClass
import com.example.astetmanager.data.entities.enums.Size

@Entity
data class PartType(
    @PrimaryKey(autoGenerate = true)
    val partTypeId: Int,
    val partTypeClass: PartTypeClass,
    val articular: String,
    val size: Size,
    val costRub: Int,
    val count: Int = 0
)

