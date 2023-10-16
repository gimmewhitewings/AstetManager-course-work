package com.example.astetmanager.data

import androidx.room.Database
import com.example.astetmanager.data.entities.Order
import com.example.astetmanager.data.entities.OrderPart
import com.example.astetmanager.data.entities.PartType
import com.example.astetmanager.data.entities.Task
import com.example.astetmanager.data.entities.User

@Database(
    entities = [
        Order::class,
        OrderPart::class,
        PartType::class,
        Task::class,
        User::class
    ],
    version = 1
)
abstract class AppDatabase {
    abstract fun astetDao(): AstetDao
}