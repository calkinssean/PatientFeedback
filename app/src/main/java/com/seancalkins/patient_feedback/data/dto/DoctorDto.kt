package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.seancalkins.patient_feedback.domain.model.Doctor

data class DoctorDto(
    val id: String? = null,
    val names: List<Name>? = null
)

fun DoctorDto.toDoctor(): Doctor? {
    val familyName = names?.first()?.family
    return if (familyName != null) {
        Doctor(familyName)
    } else {
        Log.e(DoctorDto::class.java.name, "Error creating doctor")
        null
    }
}