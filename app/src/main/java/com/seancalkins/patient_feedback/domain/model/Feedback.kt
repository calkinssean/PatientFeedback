package com.seancalkins.patient_feedback.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Feedback(
    var doctorRecommendation: Int = 1,
    var understoodDiagnosis: Boolean = false,
    var diagnosisFeedback: String = ""
): Parcelable