package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactMethod(
    @SerialName("system")
    val contact: String? = null,
    val value: String? = null,
    val use: String? = null
)
