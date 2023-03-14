package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.seancalkins.patient_feedback.domain.model.Appointment
import com.seancalkins.patient_feedback.domain.model.Diagnosis
import com.seancalkins.patient_feedback.domain.model.Doctor
import com.seancalkins.patient_feedback.domain.model.Patient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourceWrapper(
    val resource: Resource? = null
)

@Serializable
data class Resource (
    val resourceType: String? = null,
    val id: String? = null,
    val active: Boolean? = null,
    val name: List<Name>? = null,
    @SerialName("contact")
    val contactMethods: List<ContactMethod>? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val addresses: List<Address>? = null,
    val status: String? = null,
    @SerialName("type")
    val appointmentType: List<AppointmentType>? = null,
    val subject: ReferenceItem? = null,
    val actor: ReferenceItem? = null,
    val period: Period? = null,
    @SerialName("meta")
    val metaData: MetaData? = null,
    val code: Code? = null,
    val appointment: ReferenceItem? = null
)

fun Resource.patientDto(): PatientDto? {
   return if (id != null && active != null && name != null && contactMethods != null && gender != null && birthDate != null && addresses != null) {
        PatientDto(id, active, name, contactMethods, gender, birthDate, addresses)
    } else {
        Log.e(Resource::class.java.name, "Error creating patient dto")
        null
    }
}
fun Resource.doctorDto(): DoctorDto? {
    return if (id != null && name != null) {
        DoctorDto(id, name)
    } else {
        Log.e(Resource::class.java.name, "Error creating doctor dto")
        null
    }
}

fun Resource.appointmentDto(): AppointmentDto? {
    return if (id != null && status != null && appointmentType != null && subject != null && actor != null && period != null) {
        AppointmentDto(id, status, appointmentType, subject, actor, period)
    } else {
        Log.e(Resource::class.java.name, "Error creating appointment dto")
        null
    }
}

fun Resource.diagnosisDto(): DiagnosisDto? {
    return if (id != null && metaData != null && status != null && code != null) {
        DiagnosisDto(id, metaData, status, code)
    } else {
        Log.e(Resource::class.java.name, "Error creating diagnosis dto")
        null
    }
}