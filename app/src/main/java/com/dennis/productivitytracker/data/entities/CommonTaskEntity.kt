package com.dennis.productivitytracker.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["task", "productivity"])
data class CommonTaskEntity(
    val task: String,
    val productivity: Int
)
