package com.seancalkins.patient_feedback.domain.model

data class TodoItem(
    val id: String,
    val patient: Patient,
    val doctor: Doctor,
    val appointment: Appointment,
    val diagnosis: Diagnosis
)