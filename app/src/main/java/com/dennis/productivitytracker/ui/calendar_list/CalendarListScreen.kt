package com.dennis.productivitytracker.ui.calendar_list

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennis.productivitytracker.R
import com.dennis.productivitytracker.util.OneTimeUiEvent

@Composable
fun CalendarListScreen(
    onNavigate: (OneTimeUiEvent.Navigate) -> Unit,
    viewModel: CalendarListVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is OneTimeUiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Productivity Tracker") },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(CalendarListEvent.OnCommonTaskListClick)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_common_task_list),
                            contentDescription = "Common Tasks"
                        )
                    }
                }
            )
        }
    ) {

    }
}
