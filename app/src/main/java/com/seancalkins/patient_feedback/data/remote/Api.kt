package com.seancalkins.patient_feedback.data.remote

import com.seancalkins.patient_feedback.models.Feedback
import com.seancalkins.patient_feedback.models.TodoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("data/patient-feedback-raw-data.json")
    suspend fun getTodoItem(): Response<TodoItem>

    @GET("not/a/real/endpoint")
    suspend fun submitFeedback(feedback: Feedback): Response<Boolean>

}