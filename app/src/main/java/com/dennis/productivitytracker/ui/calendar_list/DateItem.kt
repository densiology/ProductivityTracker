package com.dennis.productivitytracker.ui.calendar_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennis.productivitytracker.data.entities.relations.DateWithTasks

@Composable
fun DateItem(
    dateWithTasks: DateWithTasks,
    viewModel: CalendarListVM = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(width = 1.dp, Color.LightGray)),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.weight(2f)
            ) {
                Text(
                    text = dateWithTasks.date.date,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    text = "Productivity",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                for (task in dateWithTasks.tasks) {
                    TaskItem(
                        task = task,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(CalendarListEvent.OnTaskClick(task))
                            }
                            .padding(16.dp)
                            .border(border = BorderStroke(width = 1.dp, Color.LightGray))
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.onEvent(CalendarListEvent.OnAddTaskClick(dateWithTasks.date.date))
                    },
            ) {
                Text(
                    text = dateWithTasks.date.productivity,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Click to add task",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}