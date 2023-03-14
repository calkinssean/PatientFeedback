package com.seancalkins.patient_feedback.presentation.todo_list.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seancalkins.patient_feedback.domain.model.TodoItem

@Composable
fun TodoItemScreenList(
    todoItems: List<TodoItem>,
    paddingValues: PaddingValues,
    onItemClick: (TodoItem) -> Unit
) {
    val lazyState = rememberLazyListState()
    LazyColumn(
        state = lazyState,
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            start = 16.dp,
            end = 16.dp
        )
    ) {
        items(todoItems) { item ->
            TodoScreenListItem(item) {
                onItemClick(item)
            }
        }
    }
}
