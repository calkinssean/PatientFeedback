package com.seancalkins.patient_feedback.presentation.todo_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seancalkins.patient_feedback.domain.model.TodoItem

@Composable
fun TodoScreenListItem(
    item: TodoItem,
    onItemClick: TodoItem.() -> Unit
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                item.onItemClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Appointment Feedback", style = MaterialTheme.typography.body1)
    }
    Divider()
}
