package com.seancalkins.patient_feedback.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object TodoList: Screen("todo_list")
    object Feedback: Screen("feedback")
    object Diagnosis: Screen("diagnosis")
    object DiagnosisResources: Screen("diagnosis_resources")
    object DiagnosisFeedback: Screen("diagnosis_feedback")
    object Summary: Screen("summary")
}