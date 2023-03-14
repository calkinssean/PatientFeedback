package com.seancalkins.patient_feedback.data.remote

import com.seancalkins.patient_feedback.data.dto.FeedbackSubmissionResponse
import com.seancalkins.patient_feedback.data.dto.Bundle
import retrofit2.Response
import retrofit2.http.GET

interface TodoItemApi {

    @GET("data/patient-feedback-raw-data.json")
    suspend fun getTodoItem(): Bundle

    // Github pages doesn't allow POST, so just pretend this is a post request
    @GET("data/feedback-submission.json")
    suspend fun submitFeedback(): FeedbackSubmissionResponse

}