package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Address(
    @SerialName("use")
    val use: String? = null,

    @SerialName("line")
    val line: List<String>? = null
)