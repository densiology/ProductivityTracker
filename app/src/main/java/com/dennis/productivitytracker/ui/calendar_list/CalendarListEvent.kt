package com.dennis.productivitytracker.ui.calendar_list

import com.dennis.productivitytracker.data.entities.TaskEntity

sealed class CalendarListEvent {
    data class OnTaskClick(val task: TaskEntity) : CalendarListEvent()
    data class OnAddTaskClick(val date: String) : CalendarListEvent()
    object OnCommonTaskListClick: CalendarListEvent()
}