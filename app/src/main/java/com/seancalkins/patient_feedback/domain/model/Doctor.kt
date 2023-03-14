package com.seancalkins.patient_feedback.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val familyName: String
): Parcelable
