package com.example.astetmanager.data

import com.example.astetmanager.data.database.AstetDao
import com.example.astetmanager.data.database.entities.Order
import com.example.astetmanager.data.database.entities.OrderPart
import com.example.astetmanager.data.database.entities.PartType
import com.example.astetmanager.data.database.entities.Task
import com.example.astetmanager.data.database.entities.User
import com.example.astetmanager.data.database.entities.enums.PartTypeClass
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val astetDao: AstetDao
) {

    fun getAllPartTypes(): Flow<List<PartType>> = astetDao.getAllPartTypesInStorage()

    suspend fun addPartType(partType: PartType) =
        astetDao.insertPartType(partType)

    suspend fun addNewOrder(order: Order): Int {
        return astetDao.insertOrder(order).toInt()
    }

    suspend fun addTask(
        orderId: Int,
        partTypeClass: PartTypeClass,
        partTypeSize: PartTypeSize,
        articular: String,
        count: Int,
        userId: Int? = null
    ) {
        val partType = astetDao.getPartTypeByArticularAndSizeAndClass(
            articular,
            partTypeSize,
            partTypeClass
        )
        if (partType == null) {
            val partTypeId = astetDao.insertPartType(
                PartType(
                    partTypeSize = partTypeSize,
                    partTypeClass = partTypeClass,
                    articular = articular
                )
            )
            repeat(count) {
                astetDao.insertTask(
                    Task(
                        orderId = orderId,
                        partTypeId = partTypeId.toInt(),
                        userId = userId
                    )
                )
            }
            astetDao.insertOrderPart(
                OrderPart(
                    orderId = orderId,
                    partTypeId = partTypeId.toInt(),
                    count = count
                )
            )
        } else {
            repeat(count) {
                astetDao.insertTask(
                    Task(
                        partTypeId = partType.partTypeId!!.toInt(),
                        orderId = orderId,
                        userId = userId
                    )
                )
            }
            astetDao.insertOrderPart(
                OrderPart(
                    orderId = orderId,
                    partTypeId = partType.partTypeId!!,
                    count = count
                )
            )
        }
    }

    fun getAllTasks() = astetDao.getAllTasks()

    fun getAllOrders() = astetDao.getAllOrders()
    suspend fun countPartTypesByArticularAndSizeAndClass(
        articular: String,
        partTypeSize: PartTypeSize,
        partTypeClass: PartTypeClass
    ): Int {
        return astetDao.countPartTypesByArticularAndSizeAndClass(
            articular = articular,
            partTypeSize = partTypeSize,
            partTypeClass = partTypeClass
        )
    }

    fun getPartTypesWithTasks() = astetDao.getPartTypesWithTasks()

    suspend fun toggleTaskCompletion(taskId: Int, completion: Boolean) {
        val task = astetDao.getTaskById(taskId)
        val newValue = task!!.copy(isCompleted = completion)
        astetDao.updateTask(newValue)
    }

    fun getOrdersWithOrderParts() = astetDao.getOrderWithOrderParts()
    suspend fun countTasksForOrderById(orderId: Int): Int = astetDao.countTasksForOrderById(orderId)
    suspend fun getPartTypeArticularById(partTypeId: Int): String = astetDao.getPartTypeArticularById(partTypeId)
}