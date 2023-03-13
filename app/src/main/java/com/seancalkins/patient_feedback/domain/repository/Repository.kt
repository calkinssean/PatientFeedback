package com.seancalkins.patient_feedback.domain.repository

import com.seancalkins.patient_feedback.models.Feedback
import com.seancalkins.patient_feedback.models.TodoItem
import retrofit2.Response

interface Repository {
    suspend fun getTodoItem(): Response<TodoItem>
    suspend fun submitFeedback(feedback: Feedback): Response<Boolean>
}