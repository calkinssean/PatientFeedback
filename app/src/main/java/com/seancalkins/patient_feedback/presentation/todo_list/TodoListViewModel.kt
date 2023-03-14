package com.seancalkins.patient_feedback.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seancalkins.patient_feedback.common.Resource
import com.seancalkins.patient_feedback.domain.use_case.get_todo_item.GetTodoItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoItemsUseCase: GetTodoItemUseCase
): ViewModel() {

    private val _state = mutableStateOf(TodoItemListState())
    val state: State<TodoItemListState> = _state

    init {
        getTodoItems()
    }

    private fun getTodoItems() {
        getTodoItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TodoItemListState(todoItems = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TodoItemListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TodoItemListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}