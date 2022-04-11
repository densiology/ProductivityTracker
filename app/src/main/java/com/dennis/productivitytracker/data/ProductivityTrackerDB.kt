package com.dennis.productivitytracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dennis.productivitytracker.data.dao.CommonTaskDao
import com.dennis.productivitytracker.data.dao.TaskDao
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity

@Database(
    entities = [TaskEntity::class, CommonTaskEntity::class],
    version = 1
)
abstract class ProductivityTrackerDB : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val commonTaskDao: CommonTaskDao
}