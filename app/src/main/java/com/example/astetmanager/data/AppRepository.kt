package com.example.astetmanager.data

import com.example.astetmanager.data.database.AstetDao
import com.example.astetmanager.data.database.entities.PartType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val astetDao: AstetDao
) {

    fun getAllPartTypes(): Flow<List<PartType>> = astetDao.getAllPartTypes()

    suspend fun addPartType(partType: PartType) =
        astetDao.insertPartType(partType)

}