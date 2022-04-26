package com.dennis.productivitytracker.ui.common_task_list

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennis.productivitytracker.util.OneTimeUiEvent

@Composable
fun CommonTaskListScreen(
    onNavigate: (OneTimeUiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: CommonTaskListVM = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Common Tasks") },
            )
        }
    ) {

    }
}