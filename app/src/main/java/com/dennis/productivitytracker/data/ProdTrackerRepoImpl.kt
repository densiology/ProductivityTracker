package com.dennis.productivitytracker.data

import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

class ProdTrackerRepoImpl(
    private val dao: ProdTrackerDao
) : ProdTrackerRepo {

    override suspend fun insertTask(task: TaskEntity) {
        dao.insertTask(task)
    }

    override suspend fun insertCommonTask(commonTask: CommonTaskEntity) {
        dao.insertCommonTask(commonTask)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        dao.deleteTask(task)
    }

    override suspend fun deleteCommonTask(commonTask: CommonTaskEntity) {
        dao.deleteCommonTask(commonTask)
    }

    override fun getTaskById(id: Int): TaskEntity? {
        return dao.getTaskById(id)
    }

    override fun getTasks(): Flow<List<TaskEntity>> {
        return dao.getTasks()
    }

    override fun getCommonTaskById(id: Int): CommonTaskEntity? {
        return dao.getCommonTaskById(id)
    }

    override fun getCommonTasks(): Flow<List<CommonTaskEntity>> {
        return dao.getCommonTasks()
    }
}