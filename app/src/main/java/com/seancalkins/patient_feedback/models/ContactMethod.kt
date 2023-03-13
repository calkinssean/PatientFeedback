package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactMethod(
    @SerialName("system")
    val contact: String? = null,

    @SerialName("value")
    val value: String? = null,

    @SerialName("use")
    val use: String? = null
)
