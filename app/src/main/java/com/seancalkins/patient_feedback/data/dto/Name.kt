package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Name (
    val text: String? = null,
    val family: String? = null,
    val given: List<String>? = null,
)