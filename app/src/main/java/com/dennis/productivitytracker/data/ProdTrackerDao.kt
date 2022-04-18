package com.dennis.productivitytracker.data

import androidx.room.*
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdTrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommonTask(commonTask: CommonTaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Delete
    suspend fun deleteCommonTask(commonTask: CommonTaskEntity)

    @Query("SELECT * FROM TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM CommonTaskEntity")
    fun getCommonTasks(): Flow<List<CommonTaskEntity>>
}