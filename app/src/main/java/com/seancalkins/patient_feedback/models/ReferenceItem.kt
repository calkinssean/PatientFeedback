package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReferenceItem(
    @SerialName("reference")
    val reference: String? = null
)