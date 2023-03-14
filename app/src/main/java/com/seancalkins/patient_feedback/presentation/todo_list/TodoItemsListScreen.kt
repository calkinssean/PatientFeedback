package com.seancalkins.patient_feedback.presentation.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.domain.model.TodoItem
import com.seancalkins.patient_feedback.presentation.common.CustomToolbar
import com.seancalkins.patient_feedback.presentation.todo_list.component.TodoItemScreenList

@Composable
fun TodoItemsScreen(
    viewModel: TodoListViewModel = hiltViewModel(),
    onStartTodoItem: (TodoItem) -> Unit
) {
    val state = viewModel.state.value
    Scaffold(
        topBar =  {
            CustomToolbar("Todo")
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                TodoItemScreenList(
                    todoItems = state.todoItems,
                    paddingValues = it,
                    onItemClick = onStartTodoItem
                )
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}

