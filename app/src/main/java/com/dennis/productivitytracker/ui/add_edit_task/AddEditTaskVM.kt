package com.dennis.productivitytracker.ui.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.data.entities.TaskEntity
import com.dennis.productivitytracker.util.OneTimeUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskVM @Inject constructor(
    private val repository: ProdTrackerRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var task by mutableStateOf<TaskEntity?>(null)
        private set

    var strTask by mutableStateOf("")
        private set

    var rating by mutableStateOf(1)
        private set

    var partitionStart by mutableStateOf(0)
        private set

    var partitionEnd by mutableStateOf(0)
        private set

    var disabledStart by mutableStateOf<List<Int>?>(null)
        private set

    var disabledEnd by mutableStateOf<List<Int>?>(null)
        private set

    private val _oneTimeUiEvent = Channel<OneTimeUiEvent>()
    val oneTimeUiEvent = _oneTimeUiEvent.receiveAsFlow()

    init {
        // Basics: Get data of received "taskId" and put it on UI.
        // DB Query: all tasks in the date.
        // For each task (except the current task), loop Ints from partitionStart to partitionEnd
        // { if ==partitionStart, put in disabledStart
        // { if !=partitionStart && != partitionEnd, put in both disabled
        // { if ==partitionEnd, put in disabledEnd
    }

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.OnAddTaskClick -> {

            }
            is AddEditTaskEvent.OnDeleteTaskClick -> {

            }
            is AddEditTaskEvent.OnRatingChange -> {

            }
            is AddEditTaskEvent.OnTaskChange -> {

            }
            is AddEditTaskEvent.OnTimeEndChange -> {
                // also add it on disabledStart
            }
            is AddEditTaskEvent.OnTimeStartChange -> {
                // also add it on disabledEnd
            }
        }
    }

    private fun sendUiEvent(event: OneTimeUiEvent) {
        viewModelScope.launch {
            _oneTimeUiEvent.send(event)
        }
    }
}