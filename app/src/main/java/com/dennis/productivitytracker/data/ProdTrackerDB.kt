package com.dennis.productivitytracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity

@Database(
    entities = [TaskEntity::class, CommonTaskEntity::class],
    version = 1
)

abstract class ProdTrackerDB : RoomDatabase() {
    abstract val prodTrackerDao: ProdTrackerDao
}