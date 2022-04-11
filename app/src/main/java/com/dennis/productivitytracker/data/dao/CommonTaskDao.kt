package com.dennis.productivitytracker.data.dao

import androidx.room.*
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommonTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommonTask(commonTask: CommonTaskEntity)

    @Delete
    suspend fun deleteCommonTask(commonTask: CommonTaskEntity)

    @Query("SELECT * FROM CommonTaskEntity")
    fun getCommonTasks(): Flow<List<CommonTaskEntity>>
}