package com.seancalkins.patient_feedback.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appointment(
    val type: String
): Parcelable