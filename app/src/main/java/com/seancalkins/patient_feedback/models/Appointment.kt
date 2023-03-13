package com.seancalkins.patient_feedback.models

data class Appointment(
    val id: String? = null,
    val status: String? = null,
    val type: AppointmentType? = null,
    val subject: ReferenceItem? = null,
    val actor: ReferenceItem? = null,
    val period: Period? = null
)