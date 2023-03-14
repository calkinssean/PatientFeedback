package com.seancalkins.patient_feedback.domain.use_case.get_todo_item

import com.seancalkins.patient_feedback.common.Resource
import com.seancalkins.patient_feedback.data.dto.toTodoItem
import com.seancalkins.patient_feedback.domain.model.TodoItem
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTodoItemUseCase @Inject constructor(
    private val repository: TodoItemRepository
) {
    operator fun invoke(): Flow<Resource<List<TodoItem>>> = flow {
        try {
            emit(Resource.Loading<List<TodoItem>>())
            val bundle = repository.getTodoItem().toTodoItem()
            bundle?.let {
                emit(Resource.Success<List<TodoItem>>(listOf(it)))
            } ?: run {
                emit(Resource.Error<List<TodoItem>>("Error with data"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<TodoItem>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<TodoItem>>("Couldn't reach server. Check your internet connection."))
        }
    }
}