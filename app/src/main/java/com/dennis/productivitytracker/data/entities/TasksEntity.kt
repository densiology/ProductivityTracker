package com.dennis.productivitytracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TasksEntity(
    val task: String,
    val productivity: Int,
    val dateStart: Long,
    val dateEnd: Long,
    @PrimaryKey val id: Int? = null
)
