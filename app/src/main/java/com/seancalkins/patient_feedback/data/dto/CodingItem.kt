package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CodingItem(
    val system: String,
    val code: String,
    val name: String
)
