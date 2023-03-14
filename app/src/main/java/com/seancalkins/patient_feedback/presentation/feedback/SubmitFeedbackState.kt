package com.seancalkins.patient_feedback.presentation.feedback

data class SubmitFeedbackState(
    val isLoading: Boolean = false,
    val error: String = ""
)
