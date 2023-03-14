package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    val lastUpdated: String? = null
)
