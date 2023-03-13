package com.seancalkins.patient_feedback.models

data class Diagnosis(
    val id: String? = null,
    val metaData: MetaData? = null,
    val status: String? = null,
    val codingItem: Code? = null
)