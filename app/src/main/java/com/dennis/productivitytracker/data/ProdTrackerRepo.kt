package com.dennis.productivitytracker.data

import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ProdTrackerRepo {

    suspend fun insertTask(task: TaskEntity)

    suspend fun insertCommonTask(commonTask: CommonTaskEntity)

    suspend fun deleteTask(task: TaskEntity)

    suspend fun deleteCommonTask(commonTask: CommonTaskEntity)

    fun getTasks(): Flow<List<TaskEntity>>

    fun getCommonTasks(): Flow<List<CommonTaskEntity>>
}