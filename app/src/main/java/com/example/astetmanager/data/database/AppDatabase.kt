package com.example.astetmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.astetmanager.data.database.entities.Order
import com.example.astetmanager.data.database.entities.OrderPart
import com.example.astetmanager.data.database.entities.PartType
import com.example.astetmanager.data.database.entities.Task
import com.example.astetmanager.data.database.entities.User

@Database(
    entities = [
        Order::class,
        OrderPart::class,
        PartType::class,
        Task::class,
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun astetDao(): AstetDao
}