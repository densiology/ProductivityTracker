package com.dennis.productivitytracker.ui.add_edit_common_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.data.entities.CommonTaskEntity
import com.dennis.productivitytracker.util.OneTimeUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCommonTaskVM @Inject constructor(
    private val repository: ProdTrackerRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var commonTask by mutableStateOf<CommonTaskEntity?>(null)
        private set

    var strCommonTask by mutableStateOf("")
        private set

    var priority by mutableStateOf(1)
        private set

    private val _oneTimeUiEvent = Channel<OneTimeUiEvent>()
    val oneTimeUiEvent = _oneTimeUiEvent.receiveAsFlow()

    init {
        val commonTaskId = savedStateHandle.get<Int>("commonTaskId")
        if (commonTaskId != -1) {
            commonTaskId?.let { ctId ->
                viewModelScope.launch {
                    repository.getCommonTaskById(ctId)?.let { commonTask ->
                        strCommonTask = commonTask.task
                        priority = commonTask.priority
                        this@AddEditCommonTaskVM.commonTask = commonTask
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditCommonTaskEvent) {
        when(event) {
            is AddEditCommonTaskEvent.OnCommonTaskChange -> {
                strCommonTask = event.commonTask
            }
            is AddEditCommonTaskEvent.OnRatingChange -> {
                priority = event.priority
            }
            is AddEditCommonTaskEvent.OnSaveCommonTaskClick -> {
                viewModelScope.launch {
                    repository.insertCommonTask(
                        CommonTaskEntity(
                            task = strCommonTask,
                            priority = priority,
                            id = commonTask?.id
                        )
                    )
                    sendUiEvent(OneTimeUiEvent.PopBackStack)
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