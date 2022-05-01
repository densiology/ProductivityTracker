package com.dennis.productivitytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dennis.productivitytracker.ui.add_edit_common_task.AddEditCommonTaskScreen
import com.dennis.productivitytracker.ui.add_edit_task.AddEditTaskScreen
import com.dennis.productivitytracker.ui.calendar_list.CalendarListScreen
import com.dennis.productivitytracker.ui.common_task_list.CommonTaskListScreen
import com.dennis.productivitytracker.ui.theme.ProductivityTrackerTheme
import com.dennis.productivitytracker.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductivityTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.CALENDAR_LIST
                ) {
                    composable(Routes.CALENDAR_LIST) {
                        CalendarListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        route = Routes.ADD_EDIT_TASK + "?taskId={taskId}",
                        arguments = listOf(
                            navArgument(name = "taskId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditTaskScreen(
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.COMMON_TASK_LIST) {
                        CommonTaskListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            },
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(
                        route = Routes.ADD_EDIT_COMMON_TASK + "?commonTaskId={commonTaskId}",
                        arguments = listOf(
                            navArgument(name = "commonTaskId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditCommonTaskScreen(
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}