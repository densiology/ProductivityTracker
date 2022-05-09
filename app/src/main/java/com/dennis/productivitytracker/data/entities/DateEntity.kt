package com.dennis.productivitytracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DateEntity(
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val productivity: String
)