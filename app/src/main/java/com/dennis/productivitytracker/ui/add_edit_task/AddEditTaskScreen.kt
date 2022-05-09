package com.dennis.productivitytracker.ui.add_edit_task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennis.productivitytracker.R
import com.dennis.productivitytracker.util.DataUtil
import com.dennis.productivitytracker.util.OneTimeUiEvent

@Composable
fun AddEditTaskScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTaskVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    var expandedPartitionStart by remember { mutableStateOf(false) }
    val iconPartitionStart = if (expandedPartitionStart)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var expandedPartitionEnd by remember { mutableStateOf(false) }
    val iconPartitionEnd = if (expandedPartitionEnd)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var expandedPriority by remember { mutableStateOf(false) }
    val iconPriority = if (expandedPriority)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    LaunchedEffect(key1 = true) {
        viewModel.oneTimeUiEvent.collect { event ->
            when (event) {
                is OneTimeUiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTaskEvent.OnAddTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Text(text = viewModel.date)
            Spacer(modifier = Modifier.height(8.dp))

            // PartitionStart
            OutlinedTextField(
                value = viewModel.partitionStart.toString(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Time Start") },
                trailingIcon = {
                    Icon(
                        imageVector = iconPartitionStart,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            expandedPartitionStart = !expandedPartitionStart
                        })
                }
            )
            DropdownMenu(
                expanded = expandedPartitionStart,
                onDismissRequest = { expandedPartitionStart = false },
            ) {
                DataUtil.getPartitionRange().forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddEditTaskEvent.OnTimeStartChange(item))
                            expandedPartitionStart = false
                        }
                    ) {
                        //Text(text = stringResource(DataUtil.getTimeResource(item)))
                        Text(text = stringArrayResource(id = R.array.time_array)[item])
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // PartitionEnd
            OutlinedTextField(
                value = viewModel.partitionEnd.toString(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Time End") },
                trailingIcon = {
                    Icon(
                        imageVector = iconPartitionEnd,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            expandedPartitionEnd = !expandedPartitionEnd
                        })
                }
            )
            DropdownMenu(
                expanded = expandedPartitionEnd,
                onDismissRequest = { expandedPartitionEnd = false },
            ) {
                DataUtil.getPartitionRange().forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddEditTaskEvent.OnTimeEndChange(item))
                            expandedPartitionEnd = false
                        }
                    ) {
                        //Text(text = stringResource(DataUtil.getTimeResource(item)))
                        Text(text = stringArrayResource(id = R.array.time_array)[item])
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Priority
            OutlinedTextField(
                value = viewModel.priority.toString(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Priority") },
                trailingIcon = {
                    Icon(
                        imageVector = iconPriority,
                        contentDescription = "",
                        modifier = Modifier.clickable { expandedPriority = !expandedPriority })
                }
            )
            DropdownMenu(
                expanded = expandedPriority,
                onDismissRequest = { expandedPriority = false },
            ) {
                DataUtil.getPriorityRange().forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddEditTaskEvent.OnPriorityChange(item))
                            expandedPriority = false
                        }
                    ) {
                        Text(text = item.toString())
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Task
            TextField(
                value = viewModel.strTask,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.OnTaskChange(it))
                },
                placeholder = {
                    Text(text = "Put task here")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}