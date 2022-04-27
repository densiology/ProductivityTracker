package com.dennis.productivitytracker.ui.add_edit_common_task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennis.productivitytracker.util.OneTimeUiEvent
import com.dennis.productivitytracker.util.Util

@Composable
fun AddEditCommonTaskScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditCommonTaskVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded)
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
                viewModel.onEvent(AddEditCommonTaskEvent.OnSaveCommonTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = viewModel.strCommonTask,
                onValueChange = {
                    viewModel.onEvent(AddEditCommonTaskEvent.OnCommonTaskChange(it))
                },
                placeholder = {
                    Text(text = "Put task here")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.rating.toString(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Rating") },
                trailingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Util.getRatingRange().forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddEditCommonTaskEvent.OnRatingChange(item))
                            expanded = false
                        }
                    ) {
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}