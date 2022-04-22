package com.dennis.productivitytracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TaskEntity(
    val task: String,
    val productivity: Int,
    val date: Date,
    val partitionStart: Int,
    val partitionEnd: Int,
    @PrimaryKey val id: Int? = null
)
