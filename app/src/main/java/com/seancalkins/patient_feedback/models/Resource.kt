package com.seancalkins.patient_feedback.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourceWrapper(
    @SerialName("resource")
    val resource: Resource? = null
)

@Serializable
data class Resource (
    @SerialName("resourceType")
    val resourceType: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("active")
    val active: Boolean? = null,

    @SerialName("name")
    val name: List<Name>? = null,

    @SerialName("contact")
    val contactMethods: List<ContactMethod>? = null,

    @SerialName("gender")
    val gender: String? = null,

    @SerialName("birthDate")
    val birthDate: String? = null,

    @SerialName("address")
    val addresses: List<Address>? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("type")
    val appointmentType: AppointmentType? = null,

    @SerialName("subject")
    val subject: ReferenceItem? = null,

    @SerialName("actor")
    val actor: ReferenceItem? = null,

    @SerialName("period")
    val period: Period? = null,

    @SerialName("meta")
    val metaData: MetaData? = null,

    @SerialName("code")
    val code: Code? = null,

    @SerialName("appointment")
    val appointment: ReferenceItem? = null

) {
    fun patient() = Patient(id, active, name, contactMethods, gender, birthDate, addresses)
    fun doctor() = Doctor(id, name)
    fun appointment() = Appointment(id, status, appointmentType, subject, actor, period)
    fun diagnosis() = Diagnosis(id, metaData, status, code)
}