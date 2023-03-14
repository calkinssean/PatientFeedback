package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.seancalkins.patient_feedback.domain.model.Diagnosis

data class DiagnosisDto(
    val id: String? = null,
    val metaData: MetaData? = null,
    val status: String? = null,
    val codingItem: Code? = null
)

fun DiagnosisDto.toDiagnosis(): Diagnosis? {
    val diagnosisName = codingItem?.coding?.first()?.name
    return if (diagnosisName != null) {
        Diagnosis(diagnosisName)
    } else {
        Log.e(DiagnosisDto::class.java.name, "Error creating diagnosis")
        null
    }
}