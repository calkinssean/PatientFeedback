package com.seancalkins.patient_feedback.presentation

sealed class Screen(val route: String) {
    object TodoList: Screen("todo_list")
    object Feedback: Screen("feedback")
    object Diagnosis: Screen("diagnosis")
    object DiagnosisResources: Screen("diagnosis_resources")
    object DiagnosisFeedback: Screen("diagnosis_feedback")
    object Summary: Screen("summary")
}