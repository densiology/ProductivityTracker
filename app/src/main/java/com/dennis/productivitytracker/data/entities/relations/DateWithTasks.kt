package com.dennis.productivitytracker.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.dennis.productivitytracker.data.entities.DateEntity
import com.dennis.productivitytracker.data.entities.TaskEntity

data class DateWithTasks(
    @Embedded val date: DateEntity,
    @Relation(
        parentColumn = "date",
        entityColumn = "date"
    )
    val tasks: List<TaskEntity>
)