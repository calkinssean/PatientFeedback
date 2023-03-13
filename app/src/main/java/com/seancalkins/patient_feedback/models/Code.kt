package com.seancalkins.patient_feedback.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Code(

    @SerialName("coding")
    val coding: List<CodingItem>? = null

)