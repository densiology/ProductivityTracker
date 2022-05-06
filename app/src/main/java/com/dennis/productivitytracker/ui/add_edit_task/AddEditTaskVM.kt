package com.dennis.productivitytracker.ui.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.data.entities.TaskEntity
import com.dennis.productivitytracker.util.DataUtil.toSimpleString
import com.dennis.productivitytracker.util.OneTimeUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
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

    var priority by mutableStateOf(1)
        private set

    var date by mutableStateOf("")
        private set

    var partitionStart by mutableStateOf(0)
        private set

    var partitionEnd by mutableStateOf(0)
        private set

    var disabledStart by mutableStateOf<MutableList<Int>?>(null)
        private set

    var disabledEnd by mutableStateOf<MutableList<Int>?>(null)
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
        val taskId = savedStateHandle.get<Int>("")
        if(taskId != -1) {
            taskId?.let { tId ->
                viewModelScope.launch {
                    repository.getTaskById(tId)?.let { task ->
                        strTask = task.task
                        priority = task.priority
                        date = task.date
                        partitionStart = task.partitionStart
                        partitionEnd = task.partitionEnd
                        this@AddEditTaskVM.task = task
                    }
                }
            }
        }
        // TODO continue with logic
    }

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.OnAddTaskClick -> {
                viewModelScope.launch {
                    repository.insertTask(
                        TaskEntity(
                            task = strTask,
                            priority = priority,
                            date = task?.date ?: Date().toSimpleString(),
                            partitionStart = partitionStart,
                            partitionEnd = partitionEnd
                        )
                    )
                    sendUiEvent(OneTimeUiEvent.PopBackStack)
                }
            }
            is AddEditTaskEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    repository.deleteTask(event.task)
                    sendUiEvent(OneTimeUiEvent.PopBackStack)
                }
            }
            is AddEditTaskEvent.OnPriorityChange -> {
                priority = event.priority
            }
            is AddEditTaskEvent.OnTaskChange -> {
                strTask = event.task
            }
            is AddEditTaskEvent.OnTimeEndChange -> {
                partitionEnd = event.partitionEnd
                disabledStart?.add(event.partitionEnd)
            }
            is AddEditTaskEvent.OnTimeStartChange -> {
                partitionStart = event.partitionStart
                disabledEnd?.add(event.partitionStart)
            }
        }
    }

    private fun sendUiEvent(event: OneTimeUiEvent) {
        viewModelScope.launch {
            _oneTimeUiEvent.send(event)
        }
    }
}