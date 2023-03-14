package com.seancalkins.patient_feedback.domain.repository

import com.seancalkins.patient_feedback.data.dto.FeedbackSubmissionResponse
import com.seancalkins.patient_feedback.data.dto.Bundle
import retrofit2.Response

interface TodoItemRepository {
    suspend fun getTodoItem(): Bundle
    suspend fun submitFeedback(): FeedbackSubmissionResponse
}