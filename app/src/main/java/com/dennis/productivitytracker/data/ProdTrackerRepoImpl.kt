package com.dennis.productivitytracker.data

import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

class ProdTrackerRepoImpl(
    private val prodTrackerDao: ProdTrackerDao
) : ProdTrackerRepo {

    override suspend fun insertTask(task: TaskEntity) {
        prodTrackerDao.insertTask(task)
    }

    override suspend fun insertCommonTask(commonTask: CommonTaskEntity) {
        prodTrackerDao.insertCommonTask(commonTask)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        prodTrackerDao.deleteTask(task)
    }

    override suspend fun deleteCommonTask(commonTask: CommonTaskEntity) {
        prodTrackerDao.deleteCommonTask(commonTask)
    }

    override fun getTasks(): Flow<List<TaskEntity>> {
        return prodTrackerDao.getTasks()
    }

    override fun getCommonTasks(): Flow<List<CommonTaskEntity>> {
        return prodTrackerDao.getCommonTasks()
    }
}