package com.seancalkins.patient_feedback.domain

import android.util.Log
import com.seancalkins.patient_feedback.domain.repository.Repository
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.ui.todo_items.TodoItemsScreenUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTodoItemsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<TodoItemsScreenUI> = flow {
        try {
            repository.getTodoItem().run {
                if (isSuccessful) {
                    body()?.let { response ->
                        emit(mapResponseToUI(response))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(GetTodoItemsUseCase::class.java.name, e.message.orEmpty())
        }
    }

    private fun mapResponseToUI(response: TodoItem): TodoItemsScreenUI {
        return TodoItemsScreenUI(
            todoItems = listOf(response)
        )
    }
}