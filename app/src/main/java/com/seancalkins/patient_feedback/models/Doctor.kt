package com.seancalkins.patient_feedback.models

data class Doctor(
    val id: String? = null,
    val names: List<Name>? = null
) {
    fun familyName(): String? {
        return names?.firstNotNullOf { it.family }
    }
}