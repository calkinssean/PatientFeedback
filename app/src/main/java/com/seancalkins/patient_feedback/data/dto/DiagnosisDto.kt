package com.seancalkins.patient_feedback.data.dto

import com.seancalkins.patient_feedback.domain.model.Diagnosis

data class DiagnosisDto(
    val id: String,
    val metaData: MetaData,
    val status: String,
    val codingItem: Code
)

fun DiagnosisDto.toDiagnosis(): Diagnosis {
    val diagnosisName = codingItem.coding.first().name
    return Diagnosis(diagnosisName)
}