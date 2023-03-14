package com.seancalkins.patient_feedback.presentation.todo_list

import com.seancalkins.patient_feedback.domain.model.TodoItem

data class TodoItemListState(
    val isLoading: Boolean = false,
    val todoItems: List<TodoItem> = emptyList(),
    val error: String = ""
)
