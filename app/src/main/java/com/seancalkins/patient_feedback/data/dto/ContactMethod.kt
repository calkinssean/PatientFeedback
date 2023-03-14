package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ContactMethod(
    val system: String,
    val value: String,
    val use: String
)
