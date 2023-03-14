package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.seancalkins.patient_feedback.domain.model.*
import kotlinx.serialization.Serializable

@Serializable
data class Bundle(
    val resourceType: String?,
    val id: String?,
    val timestamp: String,
    val entry: List<ResourceWrapper> = listOf()
)

fun Bundle.toTodoItem(): TodoItem? {
    val patient = patientDto()?.toPatient()
    val doctor = doctorDto()?.toDoctor()
    val appointment = appointmentDto()?.toAppointment()
    val diagnosis = diagnosisDto()?.toDiagnosis()
    return if (id != null && patient != null && doctor != null && appointment != null && diagnosis != null) {
        TodoItem(
            id,
            patient,
            doctor,
            appointment,
            diagnosis
        )
    } else {
        Log.e(Bundle::class.java.name, "Error creating todo item")
        null
    }
}

fun Bundle.patientDto(): PatientDto? {
    return entries().firstOrNull { it.resourceType == "Patient" }?.patientDto()
}

fun Bundle.doctorDto(): DoctorDto? {
    return entries().firstOrNull { it.resourceType == "Doctor" }?.doctorDto()
}

fun Bundle.appointmentDto(): AppointmentDto? {
    return entries().firstOrNull { it.resourceType == "Appointment" }?.appointmentDto()
}

fun Bundle.diagnosisDto(): DiagnosisDto? {
    return entries().firstOrNull { it.resourceType == "Diagnosis" }?.diagnosisDto()
}

fun Bundle.entries(): List<Resource> {
    return entry.mapNotNull { it.resource }
}