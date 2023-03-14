package com.seancalkins.patient_feedback.data.dto

import android.util.Log
import com.seancalkins.patient_feedback.domain.model.Appointment

data class AppointmentDto(
    val id: String? = null,
    val status: String? = null,
    val type: List<AppointmentType>? = null,
    val subject: ReferenceItem? = null,
    val actor: ReferenceItem? = null,
    val period: Period? = null
)

fun AppointmentDto.toAppointment(): Appointment? {
    val appointmentType = type?.first()?.text
    return if (appointmentType != null) {
        Appointment(appointmentType)
    } else {
        Log.e(AppointmentDto::class.java.name, "Error creating appointment")
        null
    }
}
