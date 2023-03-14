package com.seancalkins.patient_feedback.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoItem(
    val id: String,
    val patient: Patient,
    val doctor: Doctor,
    val appointment: Appointment,
    val diagnosis: Diagnosis
): Parcelable