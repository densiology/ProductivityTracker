package com.dennis.productivitytracker.ui.calendar_list

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dennis.productivitytracker.R
import com.dennis.productivitytracker.data.entities.TaskEntity

@Composable
fun TaskItem(
    task: TaskEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringArrayResource(id = R.array.time_array)[task.partitionStart]
                    + "-"
                    + stringArrayResource(id = R.array.time_array)[task.partitionEnd]
                    + " > " + task.task,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = task.priority.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}