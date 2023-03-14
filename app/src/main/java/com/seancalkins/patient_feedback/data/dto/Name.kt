package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Name (
    val text: String = "",
    val family: String,
    val given: List<String>,
)