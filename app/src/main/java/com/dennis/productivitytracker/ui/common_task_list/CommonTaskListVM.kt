package com.dennis.productivitytracker.ui.common_task_list

import androidx.lifecycle.ViewModel
import com.dennis.productivitytracker.data.ProdTrackerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommonTaskListVM @Inject constructor(
    private val repository: ProdTrackerRepo
) : ViewModel() {

}