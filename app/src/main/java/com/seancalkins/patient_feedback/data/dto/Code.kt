package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Code(
    val coding: List<CodingItem>? = null
)