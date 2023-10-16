package com.example.astetmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.astetmanager.data.entities.Order
import com.example.astetmanager.data.entities.OrderPart
import com.example.astetmanager.data.entities.OrderWithOrderParts
import com.example.astetmanager.data.entities.OrderWithTasks
import com.example.astetmanager.data.entities.PartType
import com.example.astetmanager.data.entities.Task
import com.example.astetmanager.data.entities.User
import com.example.astetmanager.data.entities.UserWithTasks
import com.example.astetmanager.data.entities.enums.OrderStatus
import com.example.astetmanager.data.entities.enums.PartTypeClass
import com.example.astetmanager.data.entities.enums.Size
import com.example.astetmanager.data.entities.enums.UserRole
import kotlinx.coroutines.flow.Flow

@Dao
interface AstetDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE userId = :userId")
    suspend fun getUserById(userId: Int): Flow<User?>

    @Query("SELECT * FROM User WHERE userRole = :userRole")
    suspend fun getUsersByUserRole(userRole: UserRole): Flow<List<UserRole>>

    @Query("SELECT * FROM User WHERE userId = :userId")
    suspend fun getUserWithTasksById(userId: Int): Flow<List<UserWithTasks>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM `Order` WHERE orderId = :orderId")
    suspend fun getOrderById(orderId: Int): Order?

    @Query("SELECT * FROM `Order`")
    suspend fun getAllOrders(orderId: Int): Flow<List<Order>>

    @Query("SELECT * FROM `Order` WHERE orderStatus = :orderStatus")
    suspend fun getOrdersByStatus(orderStatus: OrderStatus): Flow<List<Order>>

    @Transaction
    @Query("SELECT * FROM `Order` WHERE orderId = :orderId")
    suspend fun getOrderWithTasksById(orderId: Int): Flow<List<OrderWithTasks>>

    @Transaction
    @Query("SELECT * FROM `Order` WHERE orderId = :orderId")
    suspend fun getOrderWithOrderPartsById(orderId: Int): Flow<List<OrderWithOrderParts>>

    @Update
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)


    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    @Query("SELECT * FROM Task")
    suspend fun getAllTasks(taskId: Int): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE isCompleted = 1")
    suspend fun getCompletedTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE isCompleted = 0")
    suspend fun getUncompletedTasks(): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)


    @Insert
    suspend fun insertPartType(partType: PartType)

    @Query("SELECT * FROM PartType WHERE partTypeId = :partTypeId")
    suspend fun getPartTypeById(partTypeId: Int): PartType?

    @Query("SELECT * FROM PartType WHERE size = :size")
    suspend fun getPartTypesBySize(size: Size): Flow<List<PartType>>

    @Query("SELECT * FROM PartType WHERE articular = :articular")
    suspend fun getPartTypesByArticular(articular: String): Flow<List<PartType>>

    @Query("SELECT * FROM PartType WHERE partTypeClass = :partTypeClass")
    suspend fun getPartTypesByPartTypeClass(partTypeClass: PartTypeClass): Flow<List<PartType>>

    @Update
    suspend fun updatePartType(partType: PartType)


    @Insert
    suspend fun insertOrderPart(orderPart: OrderPart)

    @Query("SELECT * FROM OrderPart WHERE orderPartId = :orderPartId")
    suspend fun getOrderPartById(orderPartId: Int): OrderPart?

    @Update
    suspend fun updateOrderPart(orderPart: OrderPart)
}