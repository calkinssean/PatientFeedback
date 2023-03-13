package com.seancalkins.patient_feedback.ui.todo_items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.TodoListViewModel
import com.seancalkins.patient_feedback.ui.composables.FeedbackCircularProgressIndicator
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar

@Composable
fun TodoItemsScreen(
    viewModel: TodoListViewModel = hiltViewModel(),
    onStartTodoItem: (TodoItem) -> Unit
) {
    val todoScreenUI = viewModel.todoItems.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getFeedbackForm()
    }
    Scaffold(
        topBar =  {
            CustomToolbar("Todo")
        },
        content =  {
            if (todoScreenUI.value.todoItems.isEmpty()) {
                FeedbackCircularProgressIndicator(it)
            } else {
                TodoItemScreenList(
                    todoScreenUI = todoScreenUI.value,
                    paddingValues = it,
                    onItemClick = onStartTodoItem
                )
            }
        }
    )
}

private fun NavGraphBuilder.submitFeedbackGraph(navController: NavController) {

}


@Composable
fun TodoItemScreenList(
    todoScreenUI: TodoItemsScreenUI,
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
        items(todoScreenUI.todoItems) { item ->
            TodoScreenListItem(item) {
                onItemClick(item)
            }
        }
    }
}

@Composable
fun TodoScreenListItem(
    item: TodoItem,
    onItemClick: (TodoItem) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(item)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Appointment Feedback", style = MaterialTheme.typography.body1)
    }
    Divider()
}
