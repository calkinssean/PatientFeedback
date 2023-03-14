package com.seancalkins.patient_feedback.data.repository

import com.seancalkins.patient_feedback.data.remote.TodoItemApi
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import com.seancalkins.patient_feedback.data.dto.FeedbackSubmissionResponse
import com.seancalkins.patient_feedback.data.dto.Bundle
import retrofit2.Response

class TodoItemRepositoryImpl(
    private val api: TodoItemApi
): TodoItemRepository {

    override suspend fun getTodoItem(): Bundle {
        return api.getTodoItem()
    }

    override suspend fun submitFeedback(): FeedbackSubmissionResponse {
        return api.submitFeedback()
    }

}