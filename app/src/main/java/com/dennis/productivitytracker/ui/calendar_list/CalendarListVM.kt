package com.dennis.productivitytracker.ui.calendar_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.util.Routes
import com.dennis.productivitytracker.util.OneTimeUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarListVM @Inject constructor(
    private val repository: ProdTrackerRepo
) : ViewModel() {

    val tasks = repository.getTasks()

    private val _uiEvent = Channel<OneTimeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: CalendarListEvent) {
        when (event) {
            is CalendarListEvent.OnTaskClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.ADD_EDIT_TASK + "?taskId=${event.task.id}"))
            }
            is CalendarListEvent.OnAddTaskClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.ADD_EDIT_TASK + "?taskDate=${event.task.date}"))
            }
            is CalendarListEvent.OnCommonTaskListClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.COMMON_TASK_LIST))
            }

        }
    }

    private fun sendUiEvent(event: OneTimeUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}