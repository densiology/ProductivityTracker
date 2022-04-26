package com.dennis.productivitytracker.ui.common_task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.util.OneTimeUiEvent
import com.dennis.productivitytracker.util.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonTaskListVM @Inject constructor(
    private val repository: ProdTrackerRepo
) : ViewModel() {

    val commonTasks = repository.getCommonTasks()

    private val _oneTimeUiEvent =  Channel<OneTimeUiEvent>()
    val oneTimeUiEvent = _oneTimeUiEvent.receiveAsFlow()

    fun onEvent(event: CommonTaskListEvent) {
        when(event) {
            is CommonTaskListEvent.OnAddCommonTaskClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.ADD_EDIT_COMMON_TASK))
            }
            is CommonTaskListEvent.OnCommonTaskClick -> {
                sendUiEvent(OneTimeUiEvent.Navigate(Routes.ADD_EDIT_COMMON_TASK + "?commonTaskId=${event.commonTask.id}"))
            }
            is CommonTaskListEvent.OnDeleteCommonTaskClick -> {
                viewModelScope.launch {
                    repository.deleteCommonTask(event.commonTask)
                }
            }
        }
    }

    private fun sendUiEvent(event: OneTimeUiEvent) {
        viewModelScope.launch {
            _oneTimeUiEvent.send(event)
        }
    }
}