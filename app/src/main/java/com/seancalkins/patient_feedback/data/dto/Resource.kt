package com.seancalkins.patient_feedback.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResourceWrapper(
    val resource: Resource
)

@Serializable
data class Resource (
    val resourceType: String = "",
    val id: String = "",
    val active: Boolean = false,
    val name: List<Name> = listOf(),
    val contact: List<ContactMethod> = listOf(),
    val gender: String = "",
    val birthDate: String = "",
    val address: List<Address> = listOf(),
    val status: String = "",
    val type: List<AppointmentType> = listOf(),
    val subject: ReferenceItem = ReferenceItem(reference = ""),
    val actor: ReferenceItem = ReferenceItem(reference = ""),
    val period: Period = Period(start = "", end = ""),
    val meta: MetaData = MetaData(lastUpdated = ""),
    val code: Code = Code(coding = listOf()),
    val appointment: ReferenceItem = ReferenceItem(reference = "")
)

fun Resource.patientDto(): PatientDto = PatientDto(id, active, name, contact, gender, birthDate, address)
fun Resource.doctorDto(): DoctorDto = DoctorDto(id, name)
fun Resource.appointmentDto(): AppointmentDto = AppointmentDto(id, status, type, subject, actor, period)
fun Resource.diagnosisDto(): DiagnosisDto = DiagnosisDto(id, meta, status, code)