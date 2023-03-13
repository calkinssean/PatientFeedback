package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Period(
    @SerialName("start")
    val start: String? = null,

    @SerialName("end")
    val end: String? = null
)