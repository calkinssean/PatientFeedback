package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.seancalkins.patient_feedback.domain.model.Patient

data class PatientDto(
    val id: String,
    val active: Boolean,
    val names: List<Name>,
    val contactMethods: List<ContactMethod>,
    val gender: String,
    val birthDate: String,
    val addresses: List<Address>
)

fun PatientDto.toPatient(): Patient {
    val givenName = names.first().given.first()
    val familyName = names.first().family
    return Patient(
        givenName = givenName,
        familyName = familyName
    )
}