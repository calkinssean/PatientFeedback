package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class AppointmentType(
    val text: String
)