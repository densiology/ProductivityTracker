package com.dennis.productivitytracker.data

import androidx.room.*
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.DateEntity
import com.dennis.productivitytracker.data.entities.TaskEntity
import com.dennis.productivitytracker.data.entities.relations.DateWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdTrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDate(date: DateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommonTask(commonTask: CommonTaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Delete
    suspend fun deleteCommonTask(commonTask: CommonTaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getTaskById(id: Int): TaskEntity?

    @Transaction
    @Query("SELECT * FROM DateEntity ORDER BY date DESC")
    fun getTasks(): Flow<List<DateWithTasks>>

    @Query("SELECT * FROM CommonTaskEntity WHERE id = :id")
    fun getCommonTaskById(id: Int): CommonTaskEntity?

    @Query("SELECT * FROM CommonTaskEntity")
    fun getCommonTasks(): Flow<List<CommonTaskEntity>>
}