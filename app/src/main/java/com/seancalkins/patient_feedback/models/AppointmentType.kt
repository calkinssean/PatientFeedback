package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AppointmentType(
    @SerialName("text")
    val text: String? = null
)