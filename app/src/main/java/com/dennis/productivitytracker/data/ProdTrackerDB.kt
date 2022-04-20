package com.dennis.productivitytracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dennis.productivitytracker.RoomConverters
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.data.entities.TaskEntity

@Database(
    entities = [TaskEntity::class, CommonTaskEntity::class],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class ProdTrackerDB : RoomDatabase() {
    abstract val dao: ProdTrackerDao
}