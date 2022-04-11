package com.dennis.productivitytracker.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["task", "productivity"])
data class CommonTasksEntity(
    val task: String,
    val productivity: Int
)
