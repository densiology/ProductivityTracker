package com.dennis.productivitytracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommonTaskEntity(
    val task: String,
    val priority: Int,
    @PrimaryKey val id: Int? = null
)
