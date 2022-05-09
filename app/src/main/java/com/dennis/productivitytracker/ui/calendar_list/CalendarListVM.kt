package com.dennis.productivitytracker.ui.calendar_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.data.entities.DateEntity
import com.dennis.productivitytracker.util.DataUtil.toDate
import com.dennis.productivitytracker.util.DataUtil.toSimpleString
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
        viewModelScope.launch {
            datesWithTasks.collect { list ->
                val currentDate = Date()
                if (list.isNotEmpty()) {
                    val newestDate = list.first().date.date.toDate()
                    if (currentDate.after(newestDate)) {
                        repository.insertDate(
                            DateEntity(
                                date = currentDate.toSimpleString(),
                                productivity = "0"
                            )
                        )
                    }
                } else {
                    repository.insertDate(
                        DateEntity(
                            date = currentDate.toSimpleString(),
                            productivity = "0"
                        )
                    )
                }
                // TODO add rest of dates (and change logic of dates)
//                val oldestDate = list.last().date.date.toDate()
//                if (currentDate.after(oldestDate)) {
//                    // fill dates from oldest to current
//                } else if (currentDate.before(oldestDate)) {
//                    // just add the current date to DB
//                }
            }
        }
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