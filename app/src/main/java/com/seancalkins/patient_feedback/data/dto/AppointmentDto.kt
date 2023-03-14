package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.seancalkins.patient_feedback.domain.model.Appointment

data class AppointmentDto(
    val id: String,
    val status: String,
    val type: List<AppointmentType>,
    val subject: ReferenceItem,
    val actor: ReferenceItem,
    val period: Period
)

fun AppointmentDto.toAppointment(): Appointment {
    val appointmentType = type.first().text
    return Appointment(appointmentType)
}
