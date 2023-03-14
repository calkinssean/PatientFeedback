package com.seancalkins.patient_feedback.domain.use_case.submit_feedback

import com.seancalkins.patient_feedback.common.Resource
import com.seancalkins.patient_feedback.data.dto.FeedbackSubmissionResponse
import com.seancalkins.patient_feedback.data.dto.toTodoItem
import com.seancalkins.patient_feedback.domain.model.TodoItem
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SubmitFeedbackUseCase @Inject constructor(
    private val repository: TodoItemRepository
) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val response = repository.submitFeedback()
            emit(Resource.Success<Boolean>(true))
        } catch (e: HttpException) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Boolean>("Couldn't reach server. Check your internet connection."))
        }
    }
}