package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CodingItem(
    val system: String? = null,
    val code: String? = null,
    val name: String? = null
)
