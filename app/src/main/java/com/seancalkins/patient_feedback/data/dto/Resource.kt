package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResourceWrapper(
    val resource: Resource
)

@Serializable
data class Resource (
    val resourceType: String,
    val id: String,
    val active: Boolean,
    val name: List<Name>,
    val contact: List<ContactMethod>,
    val gender: String,
    val birthDate: String,
    val address: List<Address>,
    val status: String,
    val type: List<AppointmentType>,
    val subject: ReferenceItem,
    val actor: ReferenceItem,
    val period: Period,
    val meta: MetaData,
    val code: Code,
    val appointment: ReferenceItem
)

fun Resource.patientDto(): PatientDto = PatientDto(id, active, name, contact, gender, birthDate, address)
fun Resource.doctorDto(): DoctorDto = DoctorDto(id, name)
fun Resource.appointmentDto(): AppointmentDto = AppointmentDto(id, status, type, subject, actor, period)
fun Resource.diagnosisDto(): DiagnosisDto = DiagnosisDto(id, meta, status, code)