package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val use: String? = null,
    val line: List<String>? = null
)