package com.example.astetmanager.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.astetmanager.data.database.AppDatabase
import com.example.astetmanager.data.database.AstetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideAstetDao(appDatabase: AppDatabase): AstetDao {
        return appDatabase.astetDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "astet_manager"
        ).build()
    }
}