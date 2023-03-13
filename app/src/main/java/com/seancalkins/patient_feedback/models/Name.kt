package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name (
    @SerialName("text")
    val text: String? = null,

    @SerialName("family")
    val family: String? = null,

    @SerialName("given")
    val given: List<String>? = null,
)