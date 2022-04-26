package com.dennis.productivitytracker.ui.add_edit_common_task

sealed class AddEditCommonTaskEvent {
    data class OnCommonTaskChange(val commonTask: String) : AddEditCommonTaskEvent()
    data class OnRatingChange(val rating: Int) : AddEditCommonTaskEvent()
    object OnSaveCommonTaskClick : AddEditCommonTaskEvent()
}
