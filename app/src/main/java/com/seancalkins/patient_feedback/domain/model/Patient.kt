package com.seancalkins.patient_feedback.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Patient(
    val givenName: String,
    val familyName: String
): Parcelable