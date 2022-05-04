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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarListVM @Inject constructor(
    private val repository: ProdTrackerRepo
) : ViewModel() {

    val datesWithTasks = repository.getTasks()

    private val _oneTimeUiEvent = Channel<OneTimeUiEvent>()
    val oneTimeUiEvent = _oneTimeUiEvent.receiveAsFlow()

    init {
        // get current date
        // if current date is higher than the latest date in DB {
        // { just fill new dates from latest date in DB to current date
        // else if current date is lower than the latest date in DB {
        //     && current date is higher than oldest date in DB { (not priority)
        //     { fill new dates from oldest date in DB to current date
    }

    fun onEvent(event: CalendarListEvent) {
        when (event) {
            is CalendarListEvent.OnTaskClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.ADD_EDIT_TASK + "?taskId=${event.task.id}"))
            }
            is CalendarListEvent.OnAddTaskClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.ADD_EDIT_TASK + "?taskDate=${event.date}"))
            }
            is CalendarListEvent.OnCommonTaskListClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.COMMON_TASK_LIST))
            }

        }
    }

    private fun sendUiEvent(event: OneTimeUiEvent) {
        viewModelScope.launch {
            _oneTimeUiEvent.send(event)
        }
    }
}