package com.dennis.productivitytracker.ui.common_task_list

import com.dennis.productivitytracker.data.entities.CommonTaskEntity

sealed class CommonTaskListEvent {
    data class OnCommonTaskClick(val commonTask: CommonTaskEntity) : CommonTaskListEvent()
    data class OnDeleteCommonTaskClick(val commonTask: CommonTaskEntity) : CommonTaskListEvent()
    object OnAddCommonTaskClick : CommonTaskListEvent()
}