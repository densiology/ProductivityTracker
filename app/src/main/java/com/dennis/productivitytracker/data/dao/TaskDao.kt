package com.dennis.productivitytracker.data.dao

import androidx.room.*
import com.dennis.productivitytracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>
}