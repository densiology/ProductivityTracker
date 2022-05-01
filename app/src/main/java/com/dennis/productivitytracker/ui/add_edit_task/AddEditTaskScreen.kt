package com.dennis.productivitytracker.ui.add_edit_task

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddEditTaskScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTaskVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
}