package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.seancalkins.patient_feedback.domain.model.Patient

data class PatientDto(
    val id: String? = null,
    val active: Boolean? = null,
    @SerializedName("name")
    val names: List<Name>? = null,
    @SerializedName("contact")
    val contactMethods: List<ContactMethod>? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val addresses: List<Address>? = null
)

fun PatientDto.toPatient(): Patient? {
    val givenName = names?.first()?.given?.first()
    val familyName = names?.first()?.family
    if (givenName != null && familyName != null) {
        return Patient(
            givenName = givenName,
            familyName = familyName
        )
    }
    Log.e(PatientDto::class.java.name, "Error creating patient")
    return null
}