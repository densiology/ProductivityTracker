package com.dennis.productivitytracker.ui.calendar_list

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dennis.productivitytracker.data.entities.TaskEntity
import com.dennis.productivitytracker.util.DataUtil

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
            text = stringResource(id = DataUtil.getTimeResource(task.partitionStart))
                    + "-"
                    + stringResource(id = DataUtil.getTimeResource(task.partitionEnd))
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