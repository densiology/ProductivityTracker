package com.dennis.productivitytracker.ui.add_edit_task

import com.dennis.productivitytracker.data.entities.TaskEntity

sealed class AddEditTaskEvent {
    data class OnTaskChange(val task: String) : AddEditTaskEvent()
    data class OnPriorityChange(val priority: Int) : AddEditTaskEvent()
    data class OnTimeStartChange(val partitionStart: Int) : AddEditTaskEvent()
    data class OnTimeEndChange(val partitionEnd: Int) : AddEditTaskEvent()
    data class OnDeleteTaskClick(val task: TaskEntity) : AddEditTaskEvent()
    object OnAddTaskClick : AddEditTaskEvent()
}
