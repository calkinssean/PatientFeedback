package com.seancalkins.patient_feedback.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seancalkins.patient_feedback.domain.GetTodoItemsUseCase
import com.seancalkins.patient_feedback.domain.repository.Repository
import com.seancalkins.patient_feedback.ui.todo_items.TodoItemsScreenUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoItemsUseCase: GetTodoItemsUseCase
): ViewModel() {

    private val _todoItems: MutableStateFlow<TodoItemsScreenUI> = MutableStateFlow(
        TodoItemsScreenUI(emptyList())
    )
    val todoItems = _todoItems.asStateFlow()

    internal fun getFeedbackForm() {
        getTodoItemsUseCase
        viewModelScope.launch {
            getTodoItemsUseCase().collect {
                _todoItems.value = it
            }
        }
    }

}