package com.dennis.productivitytracker.ui.common_task_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennis.productivitytracker.util.OneTimeUiEvent

@Composable
fun CommonTaskListScreen(
    onNavigate: (OneTimeUiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: CommonTaskListVM = hiltViewModel()
) {
    val commonTasks = viewModel.commonTasks.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.oneTimeUiEvent.collect { event ->
            when(event) {
                is OneTimeUiEvent.Navigate -> onNavigate(event)
                is OneTimeUiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Common Tasks") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(CommonTaskListEvent.OnAddCommonTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add common task"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(commonTasks.value){ commonTask ->
                CommonTaskItem(
                    commonTask = commonTask,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(CommonTaskListEvent.OnCommonTaskClick(commonTask))
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}