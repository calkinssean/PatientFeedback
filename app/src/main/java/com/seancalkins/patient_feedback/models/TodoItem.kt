package com.seancalkins.patient_feedback.models



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TodoItem(
    @SerialName("resourceType")
    val resourceType: String?,

    @SerialName("id")
    val id: String?,

    @SerialName("timestamp")
    val timestamp: String? = null,

    @SerialName("entry")
    val entry: List<ResourceWrapper> = listOf()
) {

    private val entries: List<Resource>
        get() = entry.mapNotNull { it.resource }

    val patient: Patient?
        get() = entries.firstOrNull { it.resourceType == "Patient" }?.patient()

    val doctor: Doctor?
        get() = entries.firstOrNull { it.resourceType == "Doctor" }?.doctor()

    val appointment: Appointment?
        get() = entries.firstOrNull { it.resourceType == "Appointment" }?.appointment()

    val diagnosis: Diagnosis?
        get() = entries.firstOrNull { it.resourceType == "Diagnosis" }?.diagnosis()

    fun doctorFamilyName(): String? {
        return doctor?.familyName()
    }

    fun patientGivenName(): String? {
        return patient?.givenName()
    }

    fun diagnosisName(): String? {
        return diagnosis?.codingItem?.coding?.first()?.name
    }

}