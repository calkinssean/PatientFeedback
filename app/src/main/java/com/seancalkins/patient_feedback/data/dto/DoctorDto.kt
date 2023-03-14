package com.seancalkins.patient_feedback.data.dto

import com.seancalkins.patient_feedback.domain.model.Doctor

data class DoctorDto(
    val id: String,
    val names: List<Name>
)

fun DoctorDto.toDoctor(): Doctor {
    val familyName = names.first().family
    return Doctor(familyName)
}