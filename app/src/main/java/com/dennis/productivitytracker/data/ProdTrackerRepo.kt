package com.dennis.productivitytracker.data

import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ProdTrackerRepo {

    suspend fun insertTask(task: TaskEntity)

    suspend fun insertCommonTask(commonTask: CommonTaskEntity)

    suspend fun deleteTask(task: TaskEntity)

    suspend fun deleteCommonTask(commonTask: CommonTaskEntity)

    fun getTaskById(id: Int): TaskEntity?

    fun getTasks(): Flow<List<TaskEntity>>

    fun getCommonTaskById(id: Int): CommonTaskEntity?

    fun getCommonTasks(): Flow<List<CommonTaskEntity>>
}