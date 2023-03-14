package com.seancalkins.patient_feedback.domain.model

import android.os.Parcelable
import com.seancalkins.patient_feedback.data.dto.Code
import com.seancalkins.patient_feedback.data.dto.MetaData
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diagnosis(
    val name: String
): Parcelable