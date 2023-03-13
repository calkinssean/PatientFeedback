package com.seancalkins.patient_feedback.data.repository

import com.seancalkins.patient_feedback.data.remote.Api
import com.seancalkins.patient_feedback.domain.repository.Repository
import com.seancalkins.patient_feedback.models.Feedback
import com.seancalkins.patient_feedback.models.TodoItem
import kotlinx.coroutines.delay
import retrofit2.Response

class RepositoryImpl(
    private val api: Api
): Repository {

    override suspend fun getTodoItem(): Response<TodoItem> {
        return api.getTodoItem()
    }

    override suspend fun submitFeedback(feedback: Feedback): Response<Boolean> {
        delay(1000L)
        return api.submitFeedback(feedback)
    }

}