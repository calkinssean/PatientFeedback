package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Period(
    val start: String,
    val end: String
)