package com.seancalkins.patient_feedback.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CodingItem(
    @SerialName("system")
    val system: String? = null,

    @SerialName("code")
    val code: String? = null,

    @SerialName("name")
    val name: String? = null
)
